package ru.ivanov.ecommerceplatformproject.authservice.exception;

public class UsernameIsTakenException extends RuntimeException {
    public UsernameIsTakenException(String message) {
        super(message);
    }
}
