package com.bank.core.outbox.service;
import org.springframework.stereotype.Service;

import com.bank.core.config.OutboxProperties;
import com.bank.core.outbox.repository.OutboxRepository;
import jakarta.transaction.Transactional;

import com.bank.core.outbox.entity.OutboxMessage;
import java.util.List;
import java.util.UUID;


@Service
public class OutboxClaimService {

    private final OutboxRepository repository;
    private final OutboxProperties properties;

    public OutboxClaimService(
            OutboxRepository repository,
            OutboxProperties properties) {

        this.repository = repository;
        this.properties = properties;
    }

    @Transactional
    public List<OutboxMessage> claimPendingEvents(UUID workerId) {

        return repository.claimPendingEvents(
                properties.getBatchSize(),
                workerId.toString());

    }

}