package com.banking.transferservice.application;

import com.banking.transferservice.domain.InternalTransferRequest;
import com.banking.transferservice.domain.TransferResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class TransferService {

    @Inject
    TransferEventPublisher eventPublisher;

    public TransferResult transferInternal(InternalTransferRequest request) {
        TransferResult result = new TransferResult(
                "TRX-" + UUID.randomUUID(),
                "COMPLETED",
                "Transfer processed"
        );
        eventPublisher.publishCompleted(result);
        return result;
    }
}
