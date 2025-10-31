package ru.ivanov.ecommerceplatformproject.authservice.service;

public interface VerificationCodeService {
    void saveCode(String email, String code);

    boolean verifyCode(String email, String code);

    boolean isCodeExists(String email);

    void updateCode(String email, String newCode);
}