package ru.ivanov.ecommerceplatformproject.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthUserResponse(
//        @JsonProperty("user")
//        UserDto userDto,

        @JsonProperty("accessToken")
        String accessToken,

        @JsonProperty("refreshToken")
        String refreshToken
) {
}