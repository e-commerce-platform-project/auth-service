package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakConflictException extends KeycloakException {
    public KeycloakConflictException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
