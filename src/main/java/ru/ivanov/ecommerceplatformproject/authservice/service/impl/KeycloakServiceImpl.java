package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.jboss.resteasy.specimpl.ResteasyHttpHeaders;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakClientConfig;
import ru.ivanov.ecommerceplatformproject.authservice.config.KeycloakProperties;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.kafka.KafkaProducer;
import ru.ivanov.ecommerceplatformproject.authservice.mapper.KeycloakDataMapper;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.event.KeycloakUserCreatedEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final String keycloakTokenUrl = "http://localhost:9090/realms/test-realm/protocol/openid-connect/token";
    private final Keycloak keycloak;
    private final KeycloakClientConfig config;
    private final KeycloakDataMapper keycloakDataMapper;
    private final KafkaProducer kafkaProducer;
    private final RestTemplate restTemplate;
    private final KeycloakProperties props;


    @Override
    public void createUser(CreateUserRequest request) {
        UsersResource usersResource = keycloak.realm(config.getClientRealm()).users();
        try (Response response = usersResource.create(keycloakDataMapper.toKeycloakUserRepresentation(request))) {
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                String keycloakUserId = CreatedResponseUtil.getCreatedId(response);
//                assignUserRoles(config.getClientRealm(), keycloakUserId, user.getRealmRoles());

                KeycloakUserCreatedEvent event = new KeycloakUserCreatedEvent(
                        UUID.randomUUID(),
                        LocalDateTime.now(),
                        keycloakUserId,
                        request.email(),
                        request.firstName(),
                        request.lastName(),
                        request.phone()
                );
                kafkaProducer.sendKeycloakUserCreatedEvent(event);
            }
        }
    }

    @Override
    public ApiTokenResponse loginUser(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", props.clientId());
        form.add("email", request.email());
        form.add("password", request.password());
        //addIfNotBlank(form, "client_secret", props.clientSecret());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);

        ResponseEntity<ApiTokenResponse> response = restTemplate.postForEntity(
                props.tokenUrl(),
                httpEntity,
                ApiTokenResponse.class
        );
        return response.getBody();
    }


    @Override
    public ApiTokenResponse activateUser(String keycloakUserId) {
        UserResource userResource = keycloak.realm(config.getClientRealm()).users().get(keycloakUserId);
        UserRepresentation user = userResource.toRepresentation();
        user.setEnabled(true);
        user.setEmailVerified(true);
        userResource.update(user);

//        assignDefaultRoles(keycloakUserId);
//        log.info("User activated in Keycloak: {}", keycloakUserId);
        return null;
    }

    @Override
    public ApiTokenResponse refreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grand_type", "refresh_token");
        form.add("refresh_token", refreshToken);
        form.add("client_id", props.clientId());
//        addIfNotBlank(form, "client_secret", props.clientSecret());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);

        ResponseEntity<ApiTokenResponse> response = restTemplate.postForEntity(
                props.tokenUrl(),
                entity,
                ApiTokenResponse.class
        );
        return response.getBody();
    }


    private void assignUserRoles(String realm, String userId, List<String> realmRoles) {
        UserResource userResource = keycloak.realm(realm).users().get(userId);
        realmRoles.forEach(r -> userResource.roles().realmLevel().add(Collections.singletonList(getRealmRole(realm, r))));
    }

    private RoleRepresentation getRealmRole(String realm, String realmRole) {
        return keycloak.realm(realm).roles().get(realmRole).toRepresentation();
    }

//    private void assignDefaultRoles(String userId) {
//        RealmResource realmResource = keycloak.realm(realm);
//        RoleRepresentation userRole = realmResource.roles().get("user").toRepresentation();
//
//        realmResource.users().get(userId).roles().realmLevel()
//                .add(List.of(userRole));
//    }

    public boolean isUsernameAvailable(String username) {
        return keycloak.realm(config.getClientRealm())
                .users().searchByUsername(username, true)
                .isEmpty();
    }

    public boolean isEmailAvailable(String email) {
        return keycloak.realm(config.getClientRealm())
                .users().searchByEmail(email, true)
                .isEmpty();
    }
}