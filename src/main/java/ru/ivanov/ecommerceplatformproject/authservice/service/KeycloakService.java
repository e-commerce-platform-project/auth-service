package ru.ivanov.ecommerceplatformproject.authservice.service;

import org.keycloak.representations.idm.UserRepresentation;
import ru.ivanov.ecommerceplatformproject.authservice.dto.RegisteredUserDto;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.UserLoginReguest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

public interface KeycloakService {
    RegisteredUserDto createUser(UserRepresentation keycloakUser);

    ApiTokenResponse loginUser(UserLoginReguest request);

    ApiTokenResponse activateUser(String keycloakUserId);

    ApiTokenResponse refreshToken(String request);
}
