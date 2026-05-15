package com.banking.transferservice.application;

import com.banking.transferservice.domain.InternalTransferRequest;
import com.banking.transferservice.domain.TransferResult;
import com.banking.transferservice.domain.model.OutboxEventEntity;
import com.banking.transferservice.infrastructure.persistence.OutboxRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class TransferService {

    @Inject
    TransferEventPublisher eventPublisher;

    @Inject
    OutboxRepository outboxRepository;

    @Transactional
    public TransferResult transferInternal(InternalTransferRequest request) {
        TransferResult result = new TransferResult(
                "TRX-" + UUID.randomUUID(),
                "COMPLETED",
                "Transfer processed"
        );

        OutboxEventEntity outbox = new OutboxEventEntity();
        outbox.id = UUID.randomUUID();
        outbox.aggregateType = "TRANSFER";
        outbox.aggregateId = result.transferId();
        outbox.eventType = "transfer.completed.v1";
        outbox.payload = "{\"transferId\":\"" + result.transferId() + "\",\"status\":\"COMPLETED\"}";
        outbox.status = "PENDING";
        outbox.createdAt = Instant.now();
        outboxRepository.persist(outbox);

        eventPublisher.publishCompleted(result);
        return result;
    }
}
