package com.ob11to.spring.http.rest;

import com.ob11to.spring.dto.auth.LoginRequest;
import com.ob11to.spring.dto.auth.LoginResponse;
import com.ob11to.spring.dto.auth.RefreshJwtRequest;
import com.ob11to.spring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getNewAccessToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return ResponseEntity.ok(authService.getAccessToken(refreshJwtRequest.getRefreshToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return ResponseEntity.ok(authService.refresh(refreshJwtRequest.getRefreshToken()));
    }
}
