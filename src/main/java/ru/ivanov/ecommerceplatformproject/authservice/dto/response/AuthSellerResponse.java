package ru.ivanov.ecommerceplatformproject.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthSellerResponse(
//        @JsonProperty("seller")
//        SellerDto sellerDto,

        @JsonProperty("accessToken")
        String accessToken,

        @JsonProperty("refreshToken")
        String refreshToken
) {
}