package com.spaceroom.blog.controllers;

import com.spaceroom.blog.domain.dtos.AuthResponse;
import com.spaceroom.blog.domain.dtos.LoginRequest;
import com.spaceroom.blog.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@RequiredArgsConstructor
@Slf4j // Using @Slf4j for logging
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
//        log.error("Received login request for email: {}", loginRequest.getEmail());
//        log.error("Received login request for password: {}", loginRequest.getPassword());
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400) // 24 hours in seconds
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
