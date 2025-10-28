package ru.ivanov.ecommerceplatformproject.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LogoutRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RefreshTokenRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.response.AuthSellerResponse;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.request.SellerRegistrationRequest;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestControllerV1 {

    private final AuthService authService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return authService.createUser(request);
    }

    @PostMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiTokenResponse loginUser(@Valid @RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }


    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public ApiTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
//        return authService.refreshToken();
        return null;
    }








//    @PostMapping("/seller/register")
//    public ResponseEntity<AuthSellerResponse> registerSeller(@Valid @RequestBody SellerRegistrationRequest request) {
//        AuthSellerResponse response = authService.registerSeller(request);
//        return ResponseEntity.created(null)
//                .contentType(APPLICATION_JSON)
//                .body(response);
//    }
//
//
//    @PostMapping("/seller/login")
//    public ResponseEntity<AuthSellerResponse> loginSeller(@Valid @RequestBody LoginRequest request) {
//        AuthSellerResponse response = authService.loginSeller(request);
//        return ResponseEntity.ok()
//                .contentType(APPLICATION_JSON)
//                .body(response);
//    }

//    @PostMapping("/user/refresh")
//    public ResponseEntity<JwtResponse> refreshUserToken(@Valid @RequestBody RefreshTokenRequest request) {
//        JwtResponse response = authService.refreshUser(request);
//        return ResponseEntity.ok()
//                .contentType(APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/seller/refresh")
//    public ResponseEntity<JwtResponse> refreshSellerToken(@Valid @RequestBody RefreshTokenRequest request) {
//        JwtResponse response = authService.refreshSeller(request);
//        return ResponseEntity.ok()
//                .contentType(APPLICATION_JSON)
//                .body(response);
//    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request.refreshToken());
    }
}