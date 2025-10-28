package ru.ivanov.ecommerceplatformproject.authservice.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.common.util.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    private final KeycloakClientConfig config;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(config.getServerUrl())
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();
    }
}