package ru.ivanov.ecommerceplatformproject.authservice.util;

import feign.Response;
import feign.codec.ErrorDecoder;
import ru.ivanov.ecommerceplatformproject.authservice.exception.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class KeycloakErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "";
        if (response.body() != null) {
            try {
                body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                body = "Failed to read response body: " + e.getMessage();
            }
        }

        switch (response.status()) {
            case 401 -> {
                return new KeycloakAuthException("Invalid admin credentials", body);
            }
            case 403 -> {
                return new KeycloakAuthException("Insufficient permissions", body);
            }
            case 409 -> {
                if (body.contains("UserExists")) {
                    return new KeycloakUserExistsException("User already exists", body);//todo по идее не должно быть из-за валидации
                }
                return new KeycloakConflictException("Conflict in request", body);
            }
            case 400 -> {
                if (body.contains("invalidPasswordMinLength")) {
                    return new KeycloakInvalidPasswordException("Password too short", body); //todo по идее не должно быть из-за валидации
                }
                if (body.contains("invalidEmail")) {
                    return new KeycloakInvalidEmailException("Invalid email format", body); //todo по идее не должно быть из-за валидации
                }
                return new KeycloakBadRequestException("Bad request", body);
            }
            default -> {
                return new KeycloakException("Keycloak error: " + response.status(), body);
            }
        }
    }
}
