package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;

import java.util.Map;

public interface KeycloakService {
    RegisteredUserDto createUser(RegisterUserRequest request);

    Map<String, Object> login(String email, String password);

    Map<String, Object> refreshToken(String refreshToken);

    void confirmEmail(String email);

    boolean isEmailVerified(String email);
}