package com.banking.walletservice.domain;

public record WalletPaymentResponse(String transactionId, String status, String message) {
}
