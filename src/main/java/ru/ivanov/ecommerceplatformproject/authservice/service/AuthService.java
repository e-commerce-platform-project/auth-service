package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ActivateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ResendVerificationCodeRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.UserLoginReguest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.TokenResponse;

public interface AuthService {
    ApiResponse registerUser(RegisterUserRequest request);

    TokenResponse loginUser(UserLoginReguest request);

    void logout(String refreshToken);

    TokenResponse refreshToken(String refreshToken);

    TokenResponse activateUser(ActivateUserRequest request);

    ApiResponse resendVerificationCode(ResendVerificationCodeRequest request);
}