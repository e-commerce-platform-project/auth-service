package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakBadRequestException extends KeycloakException {

    public KeycloakBadRequestException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}