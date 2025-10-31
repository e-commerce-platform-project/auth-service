package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl{
//    private final String keycloakTokenUrl = "http://localhost:9090/realms/test-realm/protocol/openid-connect/token";
//    private final Keycloak keycloak;
//    private final KeycloakClientConfig config;
//    private final RestTemplate restTemplate;
//    private final KeycloakProperties props;
//
//
//    @Override
//    public RegisteredUserDto createUser(UserRepresentation keycloakUser) {
//        UsersResource usersResource = keycloak.realm(config.getClientRealm()).users();
//        try (Response response = usersResource.create(keycloakUser)) {
//            if (response.getStatus() == HttpStatus.CREATED.value()) {
//                String userId = CreatedResponseUtil.getCreatedId(response);
////                assignUserRoles(config.getClientRealm(), userId, user.getRealmRoles()); todo
//
//                return RegisteredUserDto.builder()
//                        .userId(userId)
//                        .firstName(keycloakUser.getFirstName())
//                        .lastName(keycloakUser.getLastName())
//                        .email(keycloakUser.getEmail())
//                        .build();
//
//            } else {
//                //todo
//                return null;
//            }
//        }
//    }
//
//    @Override
//    public ApiTokenResponse loginUser(UserLoginReguest request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("grant_type", "password");
//        form.add("client_id", props.clientId());
//        form.add("email", request.email());
//        form.add("password", request.password());
//        //addIfNotBlank(form, "client_secret", props.clientSecret());
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);
//
//        ResponseEntity<ApiTokenResponse> response = restTemplate.postForEntity(
//                props.tokenUrl(),
//                httpEntity,
//                ApiTokenResponse.class
//        );
//        return response.getBody();
//    }
//
//
//    @Override
//    public ApiTokenResponse activateUser(String keycloakUserId) {
//        UserResource userResource = keycloak.realm(config.getClientRealm()).users().get(keycloakUserId);
//        UserRepresentation user = userResource.toRepresentation();
//        user.setEnabled(true);
//        user.setEmailVerified(true);
//        userResource.update(user);
//
////        assignDefaultRoles(keycloakUserId);
////        log.info("User activated in Keycloak: {}", keycloakUserId);
//        return null;
//    }
//
//    @Override
//    public ApiTokenResponse refreshToken(String refreshToken) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("grand_type", "refresh_token");
//        form.add("refresh_token", refreshToken);
//        form.add("client_id", props.clientId());
////        addIfNotBlank(form, "client_secret", props.clientSecret());
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);
//
//        ResponseEntity<ApiTokenResponse> response = restTemplate.postForEntity(
//                props.tokenUrl(),
//                entity,
//                ApiTokenResponse.class
//        );
//        return response.getBody();
//    }
//
//
//    private void assignUserRoles(String realm, String userId, List<String> realmRoles) {
//        UserResource userResource = keycloak.realm(realm).users().get(userId);
//        realmRoles.forEach(r -> userResource.roles().realmLevel().add(Collections.singletonList(getRealmRole(realm, r))));
//    }
//
//    private RoleRepresentation getRealmRole(String realm, String realmRole) {
//        return keycloak.realm(realm).roles().get(realmRole).toRepresentation();
//    }
//
////    private void assignDefaultRoles(String userId) {
////        RealmResource realmResource = keycloak.realm(realm);
////        RoleRepresentation userRole = realmResource.roles().get("user").toRepresentation();
////
////        realmResource.users().get(userId).roles().realmLevel()
////                .add(List.of(userRole));
////    }
//
//    public boolean isUsernameAvailable(String username) {
//        return keycloak.realm(config.getClientRealm())
//                .users().searchByUsername(username, true)
//                .isEmpty();
//    }
//
//    public boolean isEmailAvailable(String email) {
//        return keycloak.realm(config.getClientRealm())
//                .users().searchByEmail(email, true)
//                .isEmpty();
//    }
}