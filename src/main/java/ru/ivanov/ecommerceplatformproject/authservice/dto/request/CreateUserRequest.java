package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String email,
        String phone,
        String password
) {
}