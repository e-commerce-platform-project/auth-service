package ru.ivanov.ecommerceplatformproject.authservice.dto;

import lombok.Builder;

@Builder
public record RegisteredUserDto(
        String userId,
        String firstName,
        String lastName,
        String email
) {
}