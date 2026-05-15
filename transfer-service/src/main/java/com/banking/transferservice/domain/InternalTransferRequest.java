package com.banking.transferservice.domain;

import java.math.BigDecimal;

public record InternalTransferRequest(String sourceAccountId, String targetAccountId, BigDecimal amount, String currency, String idempotencyKey) {
}
