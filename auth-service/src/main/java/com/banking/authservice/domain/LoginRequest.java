package com.banking.authservice.domain;

public record LoginRequest(String username, String password, String mfaCode) {
}
