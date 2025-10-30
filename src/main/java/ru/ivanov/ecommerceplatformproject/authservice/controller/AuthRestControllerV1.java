package ru.ivanov.ecommerceplatformproject.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.*;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.TokenResponse;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestControllerV1 {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse loginUser(@Valid @RequestBody UserLoginReguest request) {
        return authService.loginUser(request);
    }

    @PostMapping("/resend-verification-code")
    public ApiResponse resendVerificationCode(@Valid @RequestBody ResendVerificationCodeRequest request) {
        return authService.resendVerificationCode(request);
    }


    //todo восстановление доступа (изменить пароль)


    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refreshToken(request.refreshToken());
    }

    @PostMapping("/users/activate")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse activateUser(@Valid @RequestBody ActivateUserRequest request) {
        return authService.activateUser(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request.refreshToken());
    }
}