package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class KeycloakException extends RuntimeException {

    private final String responseBody;

    public KeycloakException(String message, String responseBody) {
        super(message);
        this.responseBody = responseBody;
    }

    public KeycloakException(String message, Throwable cause) {
        super(message, cause);
        this.responseBody = null;
    }
}