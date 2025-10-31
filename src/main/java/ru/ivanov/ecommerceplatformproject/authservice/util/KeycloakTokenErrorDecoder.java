package ru.ivanov.ecommerceplatformproject.authservice.util;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class KeycloakTokenErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);

            switch (response.status()) {
                case 400:
                    if (body.contains("invalid_grant")) {
                        return new InvalidTokenException("Invalid username, password or refresh token");
                    }
                    if (body.contains("invalid_client")) {
                        return new InvalidClientException("Invalid client configuration");
                    }
                    return new TokenRequestException("Bad request: " + body);
                case 401:
                    return new InvalidTokenException("Unauthorized: " + body);
                default:
                    return new TokenRequestException("Token request failed: " + response.status() + " - " + body);
            }
        } catch (IOException e) {
            return new TokenRequestException("Failed to read token error response", e);
        }
    }

    public static class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) { super(message); }
        public InvalidTokenException(String message, Throwable cause) { super(message, cause); }
    }

    public static class InvalidClientException extends RuntimeException {
        public InvalidClientException(String message) { super(message); }
    }

    public static class TokenRequestException extends RuntimeException {
        public TokenRequestException(String message) { super(message); }
        public TokenRequestException(String message, Throwable cause) { super(message, cause); }
    }
}