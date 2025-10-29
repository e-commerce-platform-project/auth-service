package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ActivateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ResendVerificationCodeRequest;
import ru.ivanov.ecommerceplatformproject.authservice.kafka.KafkaProducer;
import ru.ivanov.ecommerceplatformproject.authservice.mapper.KeycloakDataMapper;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.UserRegisteredEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KeycloakService keycloakService;
    private final KeycloakDataMapper keycloakDataMapper;
    private final KafkaProducer kafkaProducer;

    @Override
    public ApiResponse registerUser(RegisterUserRequest request) {
        UserRepresentation keycloakUser = keycloakDataMapper.toKeycloakUser(request);
        RegisteredUserDto registeredUser = keycloakService.createUser(keycloakUser);

        String verificationCode = generateVerificationCode();
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(registeredUser.userId())
                .firstName(registeredUser.firstName())
                .lastName(registeredUser.lastName())
                .email(registeredUser.email())
                .verificationCode(verificationCode)
                .build();

        kafkaProducer.sendUserRegisteredEvent(event);
        return ApiResponse
                .success("User registered successfully");
    }

    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        Random random = ThreadLocalRandom.current();
        IntStream.range(0, 6).forEach(i -> code.append(code.append(random.nextInt(10))));
        return code.toString();
    }

    @Override
    public ApiTokenResponse loginUser(LoginRequest request) {
        return keycloakService.loginUser(request);
    }


    @Override
    @Transactional
    public void logout(String refreshToken) {

    }

    @Override
    public ApiTokenResponse refreshToken(String refreshToken) {
        return keycloakService.refreshToken(refreshToken);
    }

    @Override
    public ApiTokenResponse activateUser(ActivateUserRequest request) {
        return keycloakService.activateUser(request.userId());
    }

    @Override
    public ApiResponse resendVerificationCode(ResendVerificationCodeRequest request) {

        return null;
    }
}