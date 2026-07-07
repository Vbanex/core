package com.bank.core.outbox.repository;

import com.bank.core.outbox.entity.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository
        extends JpaRepository<OutboxMessage, Long>,
        OutboxRepositoryCustom {

}


/* 
public interface OutboxRepository
        extends JpaRepository<OutboxMessage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)

    @Query("""

        SELECT m

        FROM OutboxMessage m

        WHERE

        m.status = :status

        AND

        m.nextRetryAt <= :now

        ORDER BY m.createdAt

        """)
    List<OutboxMessage> findPendingForUpdate(OutboxStatus status, LocalDateTime now);



        @Query(value = """

            SELECT *

            FROM outbox_messages

            WHERE

                status='PENDING'

            AND

                next_retry_at <= NOW()

            ORDER BY created_at

            LIMIT :batchSize

            FOR UPDATE SKIP LOCKED

            """,

            nativeQuery = true)List<OutboxMessage> claimBatch(int batchSize);

            

}
*/