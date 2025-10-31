package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record UserLoginRequest(
        String email,
        String password
) {
}