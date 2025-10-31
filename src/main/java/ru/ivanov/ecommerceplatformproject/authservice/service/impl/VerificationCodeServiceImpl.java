package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.client.KeycloakClient;
import ru.ivanov.ecommerceplatformproject.authservice.service.VerificationCodeService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final StringRedisTemplate redisTemplate;
    private final KeycloakClient keycloakClient;


    @Override
    public String generateAndStoreCode(String email) {
        var users = keycloakClient.searchUsers(email);
        if (!users.isEmpty() && Boolean.TRUE.equals(users.get(0).get("emailVerified"))) {
            throw new IllegalStateException("Email already verified");//todo
        }


        String verificationCode = generateVerificationCode();
        redisTemplate.opsForValue().set(email, verificationCode, 2, TimeUnit.MINUTES);
        return verificationCode;
    }

    @Override
    public String generateNewCodeAndStore(String email) {
        redisTemplate.delete(email);
        return generateAndStoreCode(email);
    }

    @Override
    public boolean isCodeValid(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);

        return storedCode != null && storedCode.equals(code);
    }

    @Override
    public void deleteCode(String email) {
        redisTemplate.delete(email);
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}