package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.metrics.stats.TokenBucket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.ivanov.ecommerceplatformproject.authservice.client.KeycloakAdminClient;
import ru.ivanov.ecommerceplatformproject.authservice.client.KeycloakTokenClient;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakProperties;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.TokenResponse;
import ru.ivanov.ecommerceplatformproject.authservice.exception.KeycloakException;
import ru.ivanov.ecommerceplatformproject.authservice.exception.KeycloakInvalidEmailException;
import ru.ivanov.ecommerceplatformproject.authservice.exception.KeycloakInvalidPasswordException;
import ru.ivanov.ecommerceplatformproject.authservice.exception.KeycloakUserExistsException;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakProperties keycloakProperties;
    private final KeycloakAdminClient keycloakAdminClient;
    private final KeycloakTokenClient keycloakTokenClient;


    @Override
    public RegisteredUserDto createUser(RegisterUserRequest request) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", request.email());
        user.put("firstName", request.firstName());
        user.put("lastName", request.lastName());
        user.put("enabled", true);
        user.put("emailVerified", false);

        List<Map<String, Object>> credentials = new ArrayList<>();
        Map<String, Object> cred = new HashMap<>();
        cred.put("type", "password");
        cred.put("value", request.password());
        cred.put("temporary", false);
        credentials.add(cred);
        user.put("credentials", credentials);

        try {
            ResponseEntity<Void> response = keycloakAdminClient.createUser(user);

            String userId = extractUserIdFromLocation(response);//todo

            return RegisteredUserDto.builder()
                    .userId(userId)
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .email(request.email())
                    .build();
        } catch (KeycloakUserExistsException e) {
            throw new KeycloakUserExistsException("",e);
        } catch (KeycloakInvalidPasswordException e) {
            throw new KeycloakInvalidPasswordException("", e);
        } catch (KeycloakInvalidEmailException e) {
            throw new KeycloakInvalidEmailException("", e);
        } catch (KeycloakException e) {
            throw new RuntimeException(e);//todo
        }
    }

    @Override
    public TokenResponse login(String email, String password) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("grant_type", "password");
        request.add("client_id", keycloakProperties.clientId());
        request.add("client_secret", keycloakProperties.clientSecret());
        request.add("username", email);
        request.add("password", password);

        Map<String, Object> tokens = keycloakTokenClient.getToken(request);
        return TokenResponse.fromKeycloakResponse(tokens);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("grant_type", "refresh_token");
        request.add("client_id", keycloakProperties.clientId());
        request.add("client_secret", keycloakProperties.clientSecret());
        request.add("refresh_token", refreshToken);

        Map<String, Object> tokens = keycloakTokenClient.getToken(request);//todo обработка ошибок
        return TokenResponse.fromKeycloakResponse(tokens);
    }

    @Override
    public void confirmEmail(String email) {
        List<Map<String, Object>> users = keycloakAdminClient.searchUsers(email);
        if (!users.isEmpty()) {
            String userId = (String) users.get(0).get("id");//todo
            keycloakAdminClient.updateUser(userId, Map.of("emailVerified", true));
        }
    }

    @Override
    public boolean isEmailVerified(String email) {
        var users = keycloakAdminClient.searchUsers(email);
        if (users.isEmpty()) {
            return false;
        }
        return Boolean.TRUE.equals(users.get(0).get("emailVerified"));
    }

    private String extractUserIdFromLocation(ResponseEntity<Void> response) {
        URI location = response.getHeaders().getLocation();
        if (location == null) {
            throw new IllegalStateException("Location header missing in Keycloak response");
        }
        return location.getPath().substring(location.getPath().lastIndexOf('/') + 1);
    }
}