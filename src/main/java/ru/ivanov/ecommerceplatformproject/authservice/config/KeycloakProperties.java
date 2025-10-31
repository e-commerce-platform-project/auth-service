package ru.ivanov.ecommerceplatformproject.authservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("keycloak")
public record KeycloakProperties(
        String serverUrl,
        String realmUrl,
        String tokenUrl,
        String clientSecret,
        String clientId,
        String realm
) {
}