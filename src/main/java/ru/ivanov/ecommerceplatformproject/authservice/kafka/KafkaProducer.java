package ru.ivanov.ecommerceplatformproject.authservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.KeycloakUserCreatedEvent;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendKeycloakUserCreatedEvent(KeycloakUserCreatedEvent event) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(
                "keycloakUser-created-event-topic",
                event.email(),
                event
        );

        kafkaTemplate.send(record);
    }
}