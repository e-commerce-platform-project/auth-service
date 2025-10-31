package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}