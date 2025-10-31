package ru.ivanov.ecommerceplatformproject.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
        name = "keycloak-token",
        url = "${keycloak.serverUrl}/realms/${keycloak.realm}"
)
public interface KeycloakTokenClient {

    @PostMapping("/protocol/openid-connect/token")
    Map<String, Object> getToken(@RequestBody MultiValueMap<String, String> request);
}