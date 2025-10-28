package ru.ivanov.ecommerceplatformproject.authservice.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.ivanov.ecommerceplatformproject.common.dto.SellerDto;
import ru.ivanov.ecommerceplatformproject.common.dto.request.LoginRequest;
import ru.ivanov.ecommerceplatformproject.common.dto.request.SellerRegistrationRequest;

@FeignClient(value = "SELLER-SERVICE")
public interface SellerFeign {

    @PostMapping("/api/v1/sellers")
    ResponseEntity<SellerDto> createSeller(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody SellerRegistrationRequest request
    );

    @PostMapping("/api/v1/sellers/verify-credentials")
    ResponseEntity<SellerDto> verifyCredentials(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody LoginRequest request
    );

    @GetMapping("/api/v1/sellers")
    ResponseEntity<SellerDto> getSeller(@RequestHeader("Authorization") String authHeader);
}