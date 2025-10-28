package ru.ivanov.ecommerceplatformproject.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.ActivateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.CreateUserRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.authservice.dto.request.RefreshTokenRequest;
import ru.ivanov.ecommerceplatformproject.authservice.service.AuthService;
import ru.ivanov.ecommerceplatformproject.authservice.service.KeycloakService;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiResponse;
import ru.ivanov.ecommerceplatformproject.sharedlibs.dto.response.ApiTokenResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private final RefreshTokenService refreshTokenService;
//    private final UserClient userClient;
//    private final JWTUtils jwtUtils;
//    private final UserDtoMapper userDtoMapper;
//    private final SellerClient sellerClient;
    private KeycloakService keycloakService;

    @Override
    @Transactional
    public ApiResponse createUser(CreateUserRequest request) {
        keycloakService.createUser(request);
        return ApiResponse
                .success("Verification code sent");
    }

    @Override
    public ApiTokenResponse loginUser(LoginRequest request) {
        return keycloakService.loginUser(request);
    }

//    @Override
//    @Transactional
//    public AuthSellerResponse registerSeller(SellerRegistrationRequest request) {
//        SellerDto createdSeller = sellerClient.createSeller(request);
//
//        String accessToken = jwtUtils.generateAccessToken(createdSeller);
//        RefreshToken refreshToken = jwtUtils.generateRefreshToken(createdSeller);
//
//        refreshTokenService.save(refreshToken);
//        return new AuthSellerResponse(createdSeller, accessToken, refreshToken.getToken());
//    }

//    @Override
//    @Transactional
//    public AuthUserResponse loginUser(LoginRequest request) {
//        UserDto userDto = userClient.verifyCredentials(request);
//
//        String accessToken = jwtUtils.generateAccessToken(userDto);
//        RefreshToken refreshToken = jwtUtils.generateRefreshToken(userDto);
//
//        refreshTokenService.revokeAllTokensById(userDto.id());
//        refreshTokenService.save(refreshToken);
//
//        return new AuthUserResponse(userDto, accessToken, refreshToken.getToken());
//    }

//    @Override
//    @Transactional
//    public AuthSellerResponse loginSeller(LoginRequest request) {
//        SellerDto sellerDto = sellerClient.verifyCredentials(request);
//
//        String accessToken = jwtUtils.generateAccessToken(sellerDto);
//        RefreshToken refreshToken = jwtUtils.generateRefreshToken(sellerDto);
//
//        refreshTokenService.revokeAllTokensById(userDto.id());
//        refreshTokenService.save(refreshToken);
//        return new AuthSellerResponse(sellerDto, accessToken, refreshToken.getToken());
//    }

//    @Override
//    @Transactional
//    public JwtResponse refreshUser(RefreshTokenRequest request) {
//        String oldRefreshToken = request.refreshToken();
//
//        RefreshTokenStatus status = refreshTokenService.getTokenStatus(oldRefreshToken);
//
//        if (status != RefreshTokenStatus.ACTIVE) {
//            throw new AuthException("Недействительный токен. Статус токена: " + status.name());
//        }
//
//        UUID userId = jwtUtils.validateRefreshTokenAndExtractSubjectId(oldRefreshToken);
//
//        UserDto userDto = userClient.getUserById(userId);
//
//        String newAccessToken = jwtUtils.generateAccessToken(userDto);
//        RefreshToken newRefreshToken = jwtUtils.generateRefreshToken(userDto);
//
//        refreshTokenService.rotateToken(oldRefreshToken, newRefreshToken);
//
//        return new JwtResponse(newAccessToken, newRefreshToken.getToken());
//    }

//    @Override
//    @Transactional
//    public JwtResponse refreshSeller(RefreshTokenRequest request) {
//        String oldRefreshToken = request.refreshToken();
//
//        RefreshTokenStatus status = refreshTokenService.getTokenStatus(oldRefreshToken);
//
//        if (status != RefreshTokenStatus.ACTIVE) {
//            throw new AuthException("Недействительный токен. Статус токена: " + status.name());
//        }
//
//        UUID sellerId = jwtUtils.validateRefreshTokenAndExtractSubjectId(oldRefreshToken);
//
//        SellerDto userDto = sellerClient.getSellerById(sellerId);
//
//        String newAccessToken = jwtUtils.generateAccessToken(userDto);
//        RefreshToken newRefreshToken = jwtUtils.generateRefreshToken(userDto);
//
//        refreshTokenService.rotateToken(oldRefreshToken, newRefreshToken);
//
//        return new JwtResponse(newAccessToken, newRefreshToken.getToken());
//    }


    @Override
    @Transactional
    public void logout(String refreshToken) {
//        refreshTokenService.findByToken(token)
//                .ifPresent(refreshToken -> {
//                    if (refreshToken.isRevoked()) {
//                        return;
//                    }
//
//                    refreshTokenService.revokeToken(refreshToken.getToken());
//                });
    }

    @Override
    public ApiTokenResponse refreshToken(String refreshToken) {
        return keycloakService.refreshToken(refreshToken);
    }

    @Override
    public ApiTokenResponse activateUser(ActivateUserRequest request) {
        keycloakService.activateUser(request.keycloakUserId());
        return null;
    }
}