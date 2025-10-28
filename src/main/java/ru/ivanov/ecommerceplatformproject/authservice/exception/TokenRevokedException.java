package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class TokenRevokedException extends RuntimeException {
    public TokenRevokedException(String message) {
        super(message);
    }
}
