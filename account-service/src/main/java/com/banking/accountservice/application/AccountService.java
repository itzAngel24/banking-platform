package com.banking.accountservice.application;

import com.banking.accountservice.domain.Account;
import com.banking.accountservice.domain.CreateAccountRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.UUID;

@ApplicationScoped
public class AccountService {

    public Account create(CreateAccountRequest request) {
        return new Account(
                UUID.randomUUID(),
                request.customerId(),
                "ACC-" + System.currentTimeMillis(),
                request.accountType(),
                BigDecimal.ZERO,
                request.currency(),
                "ACTIVE"
        );
    }
}
