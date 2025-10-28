package ru.ivanov.ecommerceplatformproject.authservice.mapper;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.ap.shaded.freemarker.template.utility.CollectionUtils;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface KeycloakDataMapper {

    default UserRepresentation toKeycloakUserRepresentation(CreateUserRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setEmailVerified(false);
        user.setEnabled(false);

        user.setClientRoles(new HashMap<>());//todo узнать
        List<String> realmRoles = user.getRealmRoles();//todo
        user.setRealmRoles(CollectionUtils.isNotEmpty(realmRoles) ? realmRoles : Collections.emptyList());//todo

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.password());
        credential.setTemporary(false);
        user.setCredentials(List.of(credential));
        return user;
    }
}