package com.banking.accountservice.domain;

import java.util.UUID;

public record CreateAccountRequest(UUID customerId, String accountType, String currency) {
}
