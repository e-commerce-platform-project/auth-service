package ru.ivanov.ecommerceplatformproject.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.*;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestControllerV1 {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED) //todo или CREATED
    public ApiResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> loginUser(@Valid @RequestBody LoginRequest request) {
        return authService.loginUser(request.email(), request.password());
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> tokenRefresh(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refreshToken(request.refreshToken());
    }

    @PostMapping("/verify-code")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> verifyEmailCode(@Valid @RequestBody VerifyEmailCodeRequest request) {
        return authService.verifyEmailCodeAndLogin(request);
    }

    @PostMapping("/resend-verification-code")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse resendVerificationCode(@Valid @RequestBody ResendVerificationCodeRequest request) {
        return authService.resendVerificationCode(request.email());
    }
}