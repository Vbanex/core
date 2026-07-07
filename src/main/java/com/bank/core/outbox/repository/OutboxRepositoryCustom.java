package com.bank.core.outbox.repository;

import com.bank.core.outbox.entity.OutboxMessage;

import java.util.List;

public interface OutboxRepositoryCustom {

    List<OutboxMessage> claimPendingEvents(
            int batchSize,
            String workerId);

}