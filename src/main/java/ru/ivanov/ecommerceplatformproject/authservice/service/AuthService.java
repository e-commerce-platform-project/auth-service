package ru.ivanov.ecommerceplatformproject.authservice.service;

import jakarta.validation.Valid;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ActivateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ResendVerificationCodeRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

public interface AuthService {
    ApiResponse registerUser(RegisterUserRequest request);

    ApiTokenResponse loginUser(LoginRequest request);

    void logout(String refreshToken);

    ApiTokenResponse refreshToken(String refreshToken);

    ApiTokenResponse activateUser(ActivateUserRequest request);

    ApiResponse resendVerificationCode(ResendVerificationCodeRequest request);
}