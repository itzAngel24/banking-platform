package com.banking.accountservice.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Account(UUID id, UUID customerId, String accountNumber, String accountType, BigDecimal balance, String currency, String status) {
}
