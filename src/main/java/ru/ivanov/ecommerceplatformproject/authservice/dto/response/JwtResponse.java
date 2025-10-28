package ru.ivanov.ecommerceplatformproject.authservice.dto.response;

public record JwtResponse(
        String accessToken,
        String refreshToken
) {
}