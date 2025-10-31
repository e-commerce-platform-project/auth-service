package ru.ivanov.ecommerceplatformproject.authservice.service;

import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.VerifyEmailCodeRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;

import java.util.Map;

public interface AuthService {
    ApiResponse registerUser(RegisterUserRequest request);

    Map<String, Object> loginUser(String email, String password);

    Map<String, Object> refreshToken(String refreshToken);

    ApiResponse resendVerificationCode(String email);

    Map<String, Object> verifyEmailCodeAndLogin(VerifyEmailCodeRequest request);
}