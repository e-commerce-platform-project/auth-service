package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record LogoutRequest(
        String refreshToken
) {
}