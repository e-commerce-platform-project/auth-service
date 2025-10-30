package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record UserLoginReguest(
        String email,
        String password
) {
}