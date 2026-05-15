package com.banking.transferservice.infrastructure.persistence;

import com.banking.transferservice.domain.model.OutboxEventEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutboxRepository implements PanacheRepository<OutboxEventEntity> {
}
