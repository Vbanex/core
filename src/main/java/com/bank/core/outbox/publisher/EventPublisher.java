package com.bank.core.outbox.publisher;

import com.bank.core.outbox.entity.OutboxMessage;

public interface EventPublisher {

    void publish(OutboxMessage message);

}
