package com.banking.walletservice.domain;

import java.math.BigDecimal;

public record WalletPaymentRequest(String sourcePhone, String targetPhone, BigDecimal amount, String currency, String channel) {
}
