package ru.ivanov.ecommerceplatformproject.authservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.UserRegisteredEvent;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String userRegisteredTopic;

    public KafkaProducer(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${kafka.topics.user-registered}") String userRegisteredTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRegisteredTopic = userRegisteredTopic;
    }

    public void sendUserRegisteredEvent(UserRegisteredEvent event) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(
                userRegisteredTopic,
                event.userId(),
                event
        );
        record.headers().add("eventId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        record.headers().add("eventType", "UserRegistered".getBytes(StandardCharsets.UTF_8));
        record.headers().add("timestamp", Instant.now().toEpochMilli());
        record.headers().add("content-type",
                "application/json; event=UserRegistered; version=1.0".getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(record).
    }
}