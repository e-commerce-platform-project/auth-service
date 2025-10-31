package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.TokenResponse;

import java.util.Map;

public interface KeycloakService {
    RegisteredUserDto createUser(RegisterUserRequest request);

    TokenResponse login(String email, String password);

    TokenResponse refreshToken(String refreshToken);

    void confirmEmail(String email);

    boolean isEmailVerified(String email);
}