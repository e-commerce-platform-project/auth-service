package ru.ivanov.ecommerceplatformproject.authservice.service;

import jakarta.validation.Valid;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ActivateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RefreshTokenRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

public interface AuthService {
    ApiResponse createUser(CreateUserRequest request);

//    AuthSellerResponse registerSeller(SellerRegistrationRequest request);

    ApiTokenResponse loginUser(LoginRequest request);

//    AuthSellerResponse loginSeller(LoginRequest request);
//
//    JwtResponse refreshUser(RefreshTokenRequest request);
//    JwtResponse refreshSeller(RefreshTokenRequest request);

    void logout(String refreshToken);

    ApiTokenResponse refreshToken(String refreshToken);

    ApiTokenResponse activateUser(ActivateUserRequest request);
}