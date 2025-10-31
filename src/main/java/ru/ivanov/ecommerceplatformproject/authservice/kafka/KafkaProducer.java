package ru.ivanov.ecommerceplatformproject.authservice.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.UserRegisteredEvent;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.VerificationCodeIssuedEvent;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String userRegisteredTopic;
    private final String verificationCodeIssuedTopic;

    public KafkaProducer(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${kafka.topics.user-registered}") String userRegisteredTopic,
            @Value("${kafka.topics.verification-code-issued}") String verificationCodeIssuedTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.userRegisteredTopic = userRegisteredTopic;
        this.verificationCodeIssuedTopic = verificationCodeIssuedTopic;
    }

    public void sendUserRegisteredEvent(UserRegisteredEvent event) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(
                userRegisteredTopic,
                event.userId(),
                event
        );
        record.headers().add("eventId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        record.headers().add("eventType", "UserRegistered".getBytes(StandardCharsets.UTF_8));
//        record.headers().add("content-type",
//                "application/json; event=UserRegistered; version=1.0".getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(record);
    }

    public void sendVerificationCodeIssuedEvent(VerificationCodeIssuedEvent event) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(
                verificationCodeIssuedTopic,
                event.email(),
                event
        );
        record.headers().add("eventId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        record.headers().add("eventType", "VerificationCodeIssued".getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(record);
    }
}