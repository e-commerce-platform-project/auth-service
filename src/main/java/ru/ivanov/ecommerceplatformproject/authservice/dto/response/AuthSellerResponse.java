package ru.ivanov.ecommerceplatformproject.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ivanov.ecommerceplatformproject.common.dto.SellerDto;

public record AuthSellerResponse(
        @JsonProperty("seller")
        SellerDto sellerDto,

        @JsonProperty("accessToken")
        String accessToken,

        @JsonProperty("refreshToken")
        String refreshToken
) {
}