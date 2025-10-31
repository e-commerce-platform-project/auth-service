package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record VerifyEmailCodeRequest(
        String email,
        String code,
        String password
) {
}