package ru.ivanov.ecommerceplatformproject.authservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventHandler {
    private final RefreshTokenService refreshTokenService;

//    @KafkaListener(topics = {"user-deleted-event-topic"}, groupId = "2")
//    public void handleUserDeletedEvent(@Payload UserDeletedEvent event) {
//        // хотя при удалении аккаунта можно и удалить
//        refreshTokenService.revokeAllTokensBySubjectId(event.userId());
//    }
}