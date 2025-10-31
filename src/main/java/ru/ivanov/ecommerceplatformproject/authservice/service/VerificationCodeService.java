package ru.ivanov.ecommerceplatformproject.authservice.service;

public interface VerificationCodeService {

    String generateAndStoreCode(String email);

    String generateNewCodeAndStore(String email);

    boolean isCodeValid(String email, String code);

    void deleteCode(String email);
}