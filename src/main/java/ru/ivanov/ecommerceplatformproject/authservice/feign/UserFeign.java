package ru.ivanov.ecommerceplatformproject.authservice.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.ivanov.ecommerceplatformproject.common.dto.UserDto;
import ru.ivanov.ecommerceplatformproject.common.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.common.dto.request.UserRegistrationRequest;

@FeignClient(value = "USER-SERVICE")
public interface UserFeign {

    @PostMapping("/api/v1/users")
    ResponseEntity<UserDto> createUser(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UserRegistrationRequest request
    );

    @PostMapping("/api/v1/users/verify-credentials")
    ResponseEntity<UserDto> verifyCredentials(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody LoginRequest request
    );

    @GetMapping("/api/v1/users")
    ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authHeader);
}