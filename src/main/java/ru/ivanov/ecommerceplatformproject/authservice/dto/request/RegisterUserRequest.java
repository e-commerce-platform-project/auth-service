package ru.ivanov.ecommerceplatformproject.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank(message = "firstName must be not empty")
        String firstName,

        @NotBlank(message = "lastName must be not empty")
        String lastName,

        @NotBlank(message = "email must be not empty")
        @Email
        String email,

        @NotBlank(message = "password must be not empty")
//        @Pattern() todo
        String password
) {
}