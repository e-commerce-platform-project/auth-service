package ru.ivanov.ecommerceplatformproject.authservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivanov.ecommerceplatformproject.authservice.feign.SellerFeign;
import ru.ivanov.ecommerceplatformproject.authservice.util.JWTUtils;

@Service
@RequiredArgsConstructor
public class SellerClient {
    private final SellerFeign sellerFeign;
    private final JWTUtils jwtUtils;

//    public SellerDto createSeller(SellerRegistrationRequest request) {
//        String serviceToken = jwtUtils.generateServiceToken(null);
//
//        try {
//            String authHeader = "Bearer " + serviceToken;
//            ResponseEntity<SellerDto> response = sellerFeign.createSeller(authHeader, request);
//
//
//            if (response.getStatusCode() == HttpStatus.CREATED) {
//                return response.getBody();
//            } else {
//                throw new RuntimeException("Unexpected status: " + response.getStatusCode());
//            }
//        } catch (HttpStatusCodeException e) {
//            if (e.getStatusCode() == HttpStatus.CONFLICT) {
//                String errorResponse = e.getResponseBodyAsString();
//                throw new UsernameIsTakenException(errorResponse);
//            } else {
//                throw new RuntimeException("Seller service error: " + e.getStatusCode(), e);
//            }
//        } catch (RestClientException e) {
//            throw new RuntimeException("Network error: " + e.getMessage(), e);
//        }
//    }
//
//    public SellerDto verifyCredentials(LoginRequest request) {
//        String serviceToken = jwtUtils.generateServiceToken(null);
//
//        try {
//            String authHeader = "Bearer " + serviceToken;
//            ResponseEntity<SellerDto> response = sellerFeign.verifyCredentials(authHeader, request);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return response.getBody();
//            } else {
//                throw new RuntimeException("Failed to verify credentials. Status: " + response.getStatusCode());
//            }
//        } catch (RestClientException e) {
//            throw new RuntimeException("Error calling seller service: " + e.getMessage(), e);
//        }
//    }
//
//    public SellerDto getSellerById(UUID userId) {
//        String serviceToken = jwtUtils.generateServiceToken(userId);
//
//        try {
//            String authHeader = "Bearer " + serviceToken;
//            ResponseEntity<SellerDto> response = sellerFeign.getSeller(authHeader);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                return response.getBody();
//            } else {
//                throw new RuntimeException(response.toString());
//            }
//        } catch (RestClientException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
}
