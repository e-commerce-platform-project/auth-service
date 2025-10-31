package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.kafka.KafkaProducer;
import ru.ivanov.ecommerceplatformproject.authservice.service.NotificationService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.VerificationCodeIssuedEvent;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaProducer kafkaProducer;

    @Override
    public void sendVerificationCode(String email, String code) {
        VerificationCodeIssuedEvent event = VerificationCodeIssuedEvent.builder()
                .email(email)
                .code(code)
                .build();
        kafkaProducer.sendVerificationCodeIssuedEvent(event);
    }
}
