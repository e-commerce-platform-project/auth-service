package ru.ivanov.ecommerceplatformproject.authservice.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.Environment;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Getter
public class KeycloakClientConfig {

    public final String defaultAdminClient = "admin-cli";

    private final String masterRealm = "master";

    private final String defaultAdminClientUsername = "admin";

    private final String defaultAdminClientPassword = "admin";

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String clientRealm;

    @Value("${keycloak.realm}")
    private String realm;
}