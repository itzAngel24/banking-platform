package com.banking.walletservice.application;

import com.banking.walletservice.domain.WalletPaymentRequest;
import com.banking.walletservice.domain.WalletPaymentResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class WalletService {

    public WalletPaymentResponse send(WalletPaymentRequest request) {
        return new WalletPaymentResponse(
                "WAL-" + UUID.randomUUID(),
                "COMPLETED",
                "Wallet transfer sent to " + request.targetPhone()
        );
    }
}
