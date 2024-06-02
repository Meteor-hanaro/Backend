package com.hana.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UsersDto {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class TokenInfo {
        private String userName;
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }
}
