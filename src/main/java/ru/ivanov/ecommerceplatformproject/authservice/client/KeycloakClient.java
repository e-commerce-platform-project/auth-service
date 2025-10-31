package ru.ivanov.ecommerceplatformproject.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakFeignConfig;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(
        name = "keycloak",
        url = "${keycloak.serverUrl}/realms/${keycloak.realm}",
        configuration = KeycloakFeignConfig.class
)
public interface KeycloakClient {

    @PostMapping("/users")
    ResponseEntity<Void> createUser(@RequestBody Map<String, Object> request);

    @GetMapping("/users")
    List<Map<String, Object>> searchUsers(@RequestParam("email") String email);

    @PutMapping("/users/{userId}")
    void updateUser(@PathVariable("userId") String userId, @RequestBody Map<String, Object> updates);

    @PostMapping("/protocol/openid-connect/token")
    Map<String, Object> getToken(@RequestBody Map<String, String> request);
}