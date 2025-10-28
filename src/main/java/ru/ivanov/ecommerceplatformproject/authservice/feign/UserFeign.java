package ru.ivanov.ecommerceplatformproject.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "USER-SERVICE")
public interface UserFeign {

//    @PostMapping("/api/v1/users")
//    ResponseEntity<UserDto> createUser(
//            @RequestHeader("Authorization") String authHeader,
//            @Valid @RequestBody UserRegistrationRequest request
//    );
//
//    @PostMapping("/api/v1/users/verify-credentials")
//    ResponseEntity<UserDto> verifyCredentials(
//            @RequestHeader("Authorization") String authHeader,
//            @Valid @RequestBody LoginRequest request
//    );
//
//    @GetMapping("/api/v1/users")
//    ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authHeader);
}