package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

public record ActivateUserRequest(
        String keycloakUserId
) {
}