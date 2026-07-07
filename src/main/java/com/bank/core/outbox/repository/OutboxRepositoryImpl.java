package com.bank.core.outbox.repository;

import com.bank.core.outbox.entity.OutboxMessage;
import com.bank.core.shared.enums.OutboxStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OutboxRepositoryImpl
        implements OutboxRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<OutboxMessage> claimPendingEvents(
            int batchSize,
            String workerId) {

        return entityManager.createNativeQuery("""

WITH claimed AS (

    SELECT id

    FROM outbox_messages

    WHERE

        status = :pendingStatus

    AND

        next_retry_at <= NOW()

    ORDER BY created_at

    LIMIT :batchSize

    FOR UPDATE SKIP LOCKED

)

UPDATE outbox_messages o

SET

    status = :sendingStatus,

    processing_owner = :workerId,

    processing_started_at = NOW()

FROM claimed

WHERE o.id = claimed.id

RETURNING o.*;

""", OutboxMessage.class)

                .setParameter("batchSize", batchSize)
                .setParameter("workerId", workerId)
                .setParameter("pendingStatus", OutboxStatus.PENDING.name())
                .setParameter("sendingStatus", OutboxStatus.SENDING.name())
                .getResultList();
    }
}