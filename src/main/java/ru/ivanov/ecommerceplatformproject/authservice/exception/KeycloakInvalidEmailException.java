package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakInvalidEmailException extends KeycloakException {

    public KeycloakInvalidEmailException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakInvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}