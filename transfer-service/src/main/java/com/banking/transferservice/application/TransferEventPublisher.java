package com.banking.transferservice.application;

import com.banking.transferservice.domain.TransferResult;

public interface TransferEventPublisher {
    void publishCompleted(TransferResult result);
}
