package com.bank.core.outbox.service;

import com.bank.core.shared.enums.AggregateType;
import com.bank.core.shared.enums.EventType;
import com.bank.core.shared.events.DomainEvent;

public interface OutboxPort {

    void publishEvent(
            AggregateType aggregateType,
            Long aggregateId,
            EventType eventType,
            DomainEvent event);


}
