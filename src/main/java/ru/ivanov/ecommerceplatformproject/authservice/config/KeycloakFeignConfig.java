package ru.ivanov.ecommerceplatformproject.authservice.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.ecommerceplatformproject.authservice.util.KeycloakErrorDecoder;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KeycloakFeignConfig {

    private static final String BEARER_PREFIX = "Bearer ";
    private final KeycloakProperties keycloakProperties;
    private final RestTemplate restTemplate;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (requestTemplate.url().startsWith("/users")) {
                String clientToken = getServiceAccessToken();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + clientToken);
                requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new KeycloakErrorDecoder();
    }

    private String getServiceAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", keycloakProperties.clientId());
        params.add("client_secret", keycloakProperties.clientSecret());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.postForObject(
                keycloakProperties.tokenUrl(),
                entity,
                Map.class
        );
        return (String) response.get("access_token");//todo
    }
}