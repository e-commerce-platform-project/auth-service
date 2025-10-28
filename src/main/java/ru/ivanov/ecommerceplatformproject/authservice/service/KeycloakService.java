package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

public interface KeycloakService {
    void createUser(CreateUserRequest request);

    ApiTokenResponse loginUser(LoginRequest request);
}
