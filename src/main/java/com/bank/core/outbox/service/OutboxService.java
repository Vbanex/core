package com.bank.core.outbox.service;

import com.bank.core.outbox.entity.OutboxMessage;
import com.bank.core.outbox.repository.OutboxRepository;
import com.bank.core.shared.enums.AggregateType;
import com.bank.core.shared.enums.EventType;
import com.bank.core.shared.enums.OutboxStatus;
import com.bank.core.shared.events.DomainEvent;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
//import java.util.UUID;

@Service
public class OutboxService implements OutboxPort {

    private final OutboxRepository repository;
    private final ObjectMapper mapper;

    public OutboxService(
            OutboxRepository repository,
            ObjectMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void publishEvent(
            AggregateType aggregateType,
            Long aggregateId,
            EventType eventType,
            DomainEvent event) {

OutboxMessage message = new OutboxMessage();

message.setEventId(event.getEventId());

message.setAggregateType(aggregateType);

message.setAggregateId(aggregateId);

message.setEventType(eventType);

message.setPayload(
        mapper.valueToTree(event));

message.setStatus(OutboxStatus.PENDING);

message.setRetryCount(0);

message.setCreatedAt(LocalDateTime.now());

//

message.setNextRetryAt(LocalDateTime.now());

repository.save(message);

    }

}
