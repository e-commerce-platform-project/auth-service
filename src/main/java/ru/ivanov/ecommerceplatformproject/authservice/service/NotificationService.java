package ru.ivanov.ecommerceplatformproject.authservice.service;

public interface NotificationService {
    void sendVerificationCode(String email, String code);
}
