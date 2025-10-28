package ru.ivanov.ecommerceplatformproject.authservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import ru.ivanov.ecommerceplatformproject.authservice.exception.UsernameIsTakenException;
import ru.ivanov.ecommerceplatformproject.authservice.feign.UserFeign;
import ru.ivanov.ecommerceplatformproject.authservice.util.JWTUtils;
import ru.ivanov.ecommerceplatformproject.common.dto.UserDto;
import ru.ivanov.ecommerceplatformproject.common.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.common.dto.request.UserRegistrationRequest;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClient {

    private final JWTUtils jwtUtils;
    private final UserFeign userFeign;

    @CachePut(value = "auth-service:users", key = "#result.id")
    public UserDto createUser(UserRegistrationRequest request) {
        String serviceToken = jwtUtils.generateServiceToken(null);

        try {
            String authHeader = "Bearer " + serviceToken;
            ResponseEntity<UserDto> response = userFeign.createUser(authHeader, request);


            if (response.getStatusCode() == HttpStatus.CREATED) {
                return response.getBody();
            } else {
                throw new RuntimeException("Unexpected status: " + response.getStatusCode());
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                String errorResponse = e.getResponseBodyAsString();
                throw new UsernameIsTakenException(errorResponse);
            } else {
                throw new RuntimeException("User service error: " + e.getStatusCode(), e);
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Network error: " + e.getMessage(), e);
        }
    }

    @Cacheable(value = "auth-service:users", key = "#request.email")
    public UserDto verifyCredentials(LoginRequest request) {
        String serviceToken = jwtUtils.generateServiceToken(null);

        try {
            String authHeader = "Bearer " + serviceToken;
            ResponseEntity<UserDto> response = userFeign.verifyCredentials(authHeader, request);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println(response.getBody());
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to verify credentials. Status: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error calling userDto service: " + e.getMessage(), e);
        }
    }

    @Cacheable(value = "user-service:users", key = "#userId")
    public UserDto getUserById(UUID userId) {
        String serviceToken = jwtUtils.generateServiceToken(userId);

        try {
            String authHeader = "Bearer " + serviceToken;
            ResponseEntity<UserDto> response = userFeign.getUser(authHeader);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (RestClientException ex) {
            throw new RuntimeException(ex);
        }
    }
}