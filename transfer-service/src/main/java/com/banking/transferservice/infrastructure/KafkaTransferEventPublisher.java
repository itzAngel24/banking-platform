package com.banking.transferservice.infrastructure;

import com.banking.transferservice.application.TransferEventPublisher;
import com.banking.transferservice.domain.TransferResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Map;

@ApplicationScoped
public class KafkaTransferEventPublisher implements TransferEventPublisher {

    @Channel("transfer-completed-out")
    Emitter<Map<String, Object>> emitter;

    @Override
    public void publishCompleted(TransferResult result) {
        emitter.send(Map.of(
                "eventType", "transfer.completed.v1",
                "transferId", result.transferId(),
                "status", result.status(),
                "message", result.message()
        ));
    }
}
