package ru.ivanov.ecommerceplatformproject.authservice.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakProperties;
import ru.ivanov.ecommerceplatformproject.authservice.dto.KeycloakUserRepresentation;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.TokenRefreshRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.UserLoginReguest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.TokenResponse;

@Component
@RequiredArgsConstructor
public class KeycloakClient {
    private static final String BEARER_PREFIX = "BEARER ";
    private final RestTemplate restTemplate;
    private final KeycloakProperties keycloakProperties;

    private String userRegistrationUrl;
    private String userByIdUrl;
    private String userPasswordResetUrl;

    @PostConstruct
    public void initUrls() {
        this.userRegistrationUrl = keycloakProperties.serverUrl() + "/admin/realms/" +
                                   keycloakProperties.realm() + "/users";
        this.userByIdUrl = userRegistrationUrl + "/{id}";
        this.userPasswordResetUrl = userByIdUrl + "/reset-password";
    }


    public TokenResponse login(UserLoginReguest userLoginReguest) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("email", userLoginReguest.email());
        formData.add("password", userLoginReguest.password());
        formData.add("client_id", keycloakProperties.clientId());
        formData.add("grant_type", );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(formData, headers);

        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
                "http://keycloak:8080/realms/ecommerce/protocol/openid-connect/token",
                entity,
                TokenResponse.class
        );
        return response.getBody();//todo обработка ошибок
    }

    private TokenResponse adminLogin() {
        return null;
    }

    public TokenResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        return null;
    }

    public RegisteredUserDto registerUser(String adminToken, KeycloakUserRepresentation user) {
        return null;
    }

    public void resetUserPassword() {

    }


    public void executeOnError(String userId, String adminAccessToken, Throwable e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminAccessToken);
        headers.add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + adminAccessToken);



        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);

//        String uri = UriComponentsBuilder
//                .fromUriString(userByIdUrl)
//                .buildAndExpand(userId)
//                .toUriString();

        ResponseEntity<Void> response = restTemplate.exchange(
                userByIdUrl,
                HttpMethod.GET,
                httpEntity,
                Void.class,
                userId
        );

        //todo

    }

}