package com.banking.authservice.application;

import com.banking.authservice.domain.LoginRequest;
import com.banking.authservice.domain.TokenResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class AuthService {

    public TokenResponse login(LoginRequest request) {
        return new TokenResponse(
                "access-" + UUID.randomUUID(),
                "refresh-" + UUID.randomUUID(),
                "Bearer",
                Instant.now().plusSeconds(3600).toString(),
                request.username()
        );
    }
}
