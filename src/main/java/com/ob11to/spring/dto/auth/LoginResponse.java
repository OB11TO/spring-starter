package com.ob11to.spring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
