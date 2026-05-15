package com.banking.authservice.domain;

public record TokenResponse(String accessToken, String refreshToken, String tokenType, String expiresAt, String subject) {
}
