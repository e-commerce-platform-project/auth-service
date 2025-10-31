package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakAuthException extends KeycloakException {

    public KeycloakAuthException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}