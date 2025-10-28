package ru.ivanov.ecommerceplatformproject.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SELLER-SERVICE")
public interface SellerFeign {

//    @PostMapping("/api/v1/sellers")
//    ResponseEntity<SellerDto> createSeller(
//            @RequestHeader("Authorization") String authHeader,
//            @Valid @RequestBody SellerRegistrationRequest request
//    );
//
//    @PostMapping("/api/v1/sellers/verify-credentials")
//    ResponseEntity<SellerDto> verifyCredentials(
//            @RequestHeader("Authorization") String authHeader,
//            @Valid @RequestBody LoginRequest request
//    );
//
//    @GetMapping("/api/v1/sellers")
//    ResponseEntity<SellerDto> getSeller(@RequestHeader("Authorization") String authHeader);
}