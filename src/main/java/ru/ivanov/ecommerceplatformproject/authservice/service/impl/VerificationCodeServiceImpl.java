package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.service.VerificationCodeService;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, 2, TimeUnit.MINUTES);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);

        if(storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(email);
            return true;
        }
        return false;
    }

    public boolean isCodeExists(String email) {
        return redisTemplate.hasKey(email);
    }

    @Override
    public void updateCode(String email, String newCode) {
        redisTemplate.opsForValue().set(email, newCode, 2, TimeUnit.MINUTES);
    }
}