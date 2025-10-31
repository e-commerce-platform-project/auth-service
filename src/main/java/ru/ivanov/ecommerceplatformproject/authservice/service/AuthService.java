package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.VerifyEmailCodeRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.TokenResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;

import java.util.Map;

public interface AuthService {
    ApiResponse registerUser(RegisterUserRequest request);

    TokenResponse loginUser(String email, String password);

    TokenResponse refreshToken(String refreshToken);

    ApiResponse resendVerificationCode(String email);

    TokenResponse verifyEmailCodeAndLogin(VerifyEmailCodeRequest request);
}