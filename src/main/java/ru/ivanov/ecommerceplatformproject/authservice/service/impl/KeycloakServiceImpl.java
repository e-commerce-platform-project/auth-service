package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.client.KeycloakClient;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakProperties;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;
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

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakProperties keycloakProperties;
    private final KeycloakClient keycloakClient;

    @Override
    public RegisteredUserDto createUser(RegisterUserRequest request) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", request.email());
        user.put("firstName", request.lastName());
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
            ResponseEntity<Void> response = keycloakClient.createUser(user);

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
    public Map<String, Object> login(String email, String password) {
        Map<String, String> request = new HashMap<>();
        request.put("grant_type", "password");
        request.put("client_id", keycloakProperties.clientId());
        request.put("client_secret", keycloakProperties.clientSecret());
        request.put("email", email);
        request.put("password", password);

        return keycloakClient.getToken(request); //todo обработка ошибок
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        Map<String, String> request = new HashMap<>();
        request.put("grant_type", refreshToken);
        request.put("client_id", keycloakProperties.clientId());
        request.put("client_secret", keycloakProperties.clientSecret());
        request.put("refresh_token", refreshToken);

        return keycloakClient.getToken(request); //todo обработка ошибок
    }

    @Override
    public void confirmEmail(String email) {
        List<Map<String, Object>> users = keycloakClient.searchUsers(email);
        if (!users.isEmpty()) {
            String userId = (String) users.get(0).get("id");//todo
            keycloakClient.updateUser(userId, Map.of("emailVerified", true));
        }
    }

    @Override
    public boolean isEmailVerified(String email) {
        var users = keycloakClient.searchUsers(email);
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