package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.authservice.client.KeycloakClient;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ResendVerificationCodeRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.UserLoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.kafka.KafkaProducer;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.authservice.service.VerificationCodeService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.request.VerifyEmailCodeRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.TokenResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.UserRegisteredEvent;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.VerificationCodeIssuedEvent;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakClient keycloakClient;
    private final VerificationCodeService verificationCodeService;
    private final KafkaProducer kafkaProducer;

    @Override
    public ApiResponse registerUser(RegisterUserRequest request) {
        UserRepresentation keycloakUser = keycloakDataMapper.toKeycloakUser(request);
        RegisteredUserDto registeredUser = keycloakClient.registerUser()

        String verificationCode = generateVerificationCode();
        verificationCodeService.saveCode(request.email(), verificationCode);

        UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent.builder()
                .userId(registeredUser.userId())
                .firstName(registeredUser.firstName())
                .lastName(registeredUser.lastName())
                .email(registeredUser.email())
                .build();

        VerificationCodeIssuedEvent verificationCodeIssuedEvent = VerificationCodeIssuedEvent.builder()
                .email(registeredUser.email())
                .code(verificationCode)
                .build();

        kafkaProducer.sendUserRegisteredEvent(userRegisteredEvent);
        kafkaProducer.sendVerificationCodeIssuedEvent(verificationCodeIssuedEvent);
        return ApiResponse
                .success("User registered successfully");
    }


    @Override
    public TokenResponse loginUser(UserLoginRequest request) {
        return null;
    }


    @Override
    @Transactional
    public void logout(String refreshToken) {

    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
//        return keycloakService.refreshToken(refreshToken);
        return null;
    }

    @Override
    public TokenResponse activateUser(ActivateUserRequest request) {
//        return keycloakService.activateUser(request.userId());
        return null;
    }

    @Override
    public ApiResponse resendVerificationCode(ResendVerificationCodeRequest request) {
        if (keycloakClient.isEmailVerified(request.email())) {
            throw new IllegalStateException("Email already verified");//todo
        }

        String newVerificationCode = generateVerificationCode();

        verificationCodeService.updateCode(request.email(), newVerificationCode);

        VerificationCodeIssuedEvent verificationCodeIssuedEvent = VerificationCodeIssuedEvent.builder()
                .email(request.email())
                .code(newVerificationCode)
                .build();

        kafkaProducer.sendVerificationCodeIssuedEvent(verificationCodeIssuedEvent);
        return ApiResponse
                .success("Verification code was successfully resent");
    }

    @Override
    public TokenResponse verifyEmailCode(VerifyEmailCodeRequest request) {
//        userClient.verifyEmailCode(request);
        return null;
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}