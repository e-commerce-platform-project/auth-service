package ru.ivanov.ecommerceplatformproject.authservice.dto.response;

import java.util.Map;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        Integer expiresIn,
        Integer refreshExpiresIn,
        String tokenType
) {
    public static TokenResponse fromKeycloakResponse(Map<String, Object> keycloakResponse) {
        return new TokenResponse(
                (String) keycloakResponse.get("access_token"),
                (String) keycloakResponse.get("refresh_token"),
                (Integer) keycloakResponse.get("expires_in"),
                (Integer) keycloakResponse.get("refresh_expires_in"),
                (String) keycloakResponse.get("token_type")
        );
    }
}