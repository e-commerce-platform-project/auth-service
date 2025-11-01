package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.VerifyEmailCodeRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.TokenResponse;
import ru.ivanov.ecommerceplatformproject.authservice.kafka.KafkaProducer;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;
import ru.ivanov.ecommerceplatformproject.authservice.service.NotificationService;
import ru.ivanov.ecommerceplatformproject.authservice.service.VerificationCodeService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.UserRegisteredEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakService keycloakService;
    private final VerificationCodeService verificationCodeService;
    private final NotificationService notificationService;
    private final KafkaProducer kafkaProducer;

    @Override
    public ApiResponse registerUser(RegisterUserRequest request) {
        RegisteredUserDto registeredUser = keycloakService.createUser(request);

        String verificationCode = verificationCodeService.generateAndStoreCode(request.email());

        UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent.builder()
                .userId(registeredUser.userId())
                .firstName(registeredUser.firstName())
                .lastName(registeredUser.lastName())
                .email(registeredUser.email())
                .build();

        kafkaProducer.sendUserRegisteredEvent(userRegisteredEvent);//todo

        notificationService.sendVerificationCode(registeredUser.email(), verificationCode);
        System.out.println("возвращаю ответ");
        return ApiResponse.success("User registered successfully");
    }


    @Override
    public TokenResponse loginUser(String email, String password) {
        return keycloakService.login(email, password);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        return keycloakService.refreshToken(refreshToken);
    }

    @Override
    public ApiResponse resendVerificationCode(String email) {
        if (keycloakService.isEmailVerified(email)) {
            throw new IllegalStateException("Email already verified");//todo
        }

        String verificationCode = verificationCodeService.generateNewCodeAndStore(email);

        notificationService.sendVerificationCode(email, verificationCode);
        return ApiResponse.success("Verification code was successfully resent");
    }

    @Override
    public TokenResponse verifyEmailCodeAndLogin(VerifyEmailCodeRequest request) {
        if (!verificationCodeService.isCodeValid(request.email(), request.code())) {
            throw new IllegalArgumentException("invalid verification code");//todo
        }

        verificationCodeService.deleteCode(request.email());
        keycloakService.confirmEmail(request.email());

        return loginUser(request.email(), request.password());
    }
}