package com.bank.core.outbox.service;

import com.bank.core.outbox.entity.OutboxMessage;
import com.bank.core.outbox.repository.OutboxRepository;
import com.bank.core.shared.enums.OutboxStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OutboxStateService {

    private final OutboxRepository repository;

    public OutboxStateService(OutboxRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void markSuccess(OutboxMessage message) {

        message.setStatus(OutboxStatus.SENT);
        message.setPublishedAt(LocalDateTime.now());
        message.setLastError(null);

        repository.save(message);

    }

    @Transactional
    public void markFailure(
            OutboxMessage message,
            Exception ex) {

        message.setRetryCount(
                message.getRetryCount() + 1);

        message.setLastError(ex.getMessage());

        message.setStatus(OutboxStatus.PENDING);

        message.setNextRetryAt(

                LocalDateTime.now()

                        .plusSeconds(

                                calculateBackoff(
                                        message.getRetryCount())

                        )

        );

        repository.save(message);

    }

    private long calculateBackoff(int retryCount) {

        return Math.min(
                300,
                (long) Math.pow(2, retryCount) * 5);

    }

}
