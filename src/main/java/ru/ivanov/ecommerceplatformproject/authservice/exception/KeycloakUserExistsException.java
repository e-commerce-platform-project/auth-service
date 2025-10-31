package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakUserExistsException extends KeycloakException {

    public KeycloakUserExistsException(String message, String responseBody) {
        super(message, responseBody);
    }

    public KeycloakUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
