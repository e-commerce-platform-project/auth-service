package ru.ivanov.ecommerceplatformproject.authservice.mapper;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RegisterUserRequest;

import java.util.HashMap;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface KeycloakDataMapper {

    default UserRepresentation toKeycloakUser(RegisterUserRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setEmailVerified(false);
        user.setEnabled(true);

//        user.setClientRoles(new HashMap<>());//todo
//        List<String> realmRoles = user.getRealmRoles();//todo
//        user.setRealmRoles(CollectionUtils.isNotEmpty(realmRoles) ? realmRoles : Collections.emptyList());//todo

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.password());
        credential.setTemporary(false);
        user.setCredentials(List.of(credential));
        return user;
    }
}