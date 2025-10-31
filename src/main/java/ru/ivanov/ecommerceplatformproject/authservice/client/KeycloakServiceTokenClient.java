package ru.ivanov.ecommerceplatformproject.authservice.client;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakProperties;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KeycloakServiceTokenClient {

    private final RestTemplate restTemplate;
    private final KeycloakProperties keycloakProperties;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUrl;

    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client-credentials");
        params.add("client-id", keycloakProperties.clientId());
        params.add("client-secret", keycloakProperties.clientSecret());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(params, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.GET, entity, Map.class);
        return (String) response.getBody().get("access_token");
    }
}