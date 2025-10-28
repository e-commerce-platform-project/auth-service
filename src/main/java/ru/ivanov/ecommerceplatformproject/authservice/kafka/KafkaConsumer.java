package ru.ivanov.ecommerceplatformproject.authservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.EmailHasBeenVerifiedEvent;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KeycloakService keycloakService;


}