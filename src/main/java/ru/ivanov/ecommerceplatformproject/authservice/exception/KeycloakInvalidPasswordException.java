package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakInvalidPasswordException extends KeycloakException {

    public KeycloakInvalidPasswordException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakInvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}