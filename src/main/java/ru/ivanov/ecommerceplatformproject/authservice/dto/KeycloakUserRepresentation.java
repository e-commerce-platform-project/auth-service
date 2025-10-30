package ru.ivanov.ecommerceplatformproject.authservice.dto;

public record KeycloakUserRepresentation(
        String firstName,
        String lastName,
        String email,
        String password
) {
}