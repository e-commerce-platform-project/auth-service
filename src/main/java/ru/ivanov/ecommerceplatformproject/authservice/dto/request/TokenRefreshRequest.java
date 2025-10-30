package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record TokenRefreshRequest(
        String refreshToken
) {
}