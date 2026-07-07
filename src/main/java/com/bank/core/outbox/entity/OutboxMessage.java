package com.bank.core.outbox.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.bank.core.shared.enums.AggregateType;
import com.bank.core.shared.enums.EventType;
import com.bank.core.shared.enums.OutboxStatus;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "outbox_messages")

public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID eventId;

    //private Integer version = 1;

    //new fields
    @Column(nullable = false)
private LocalDateTime nextRetryAt;

@Column(length = 100)
private String processingOwner;

private LocalDateTime processingStartedAt;

//

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AggregateType aggregateType;

    @Column(nullable = false)
    private Long aggregateId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;

 @JdbcTypeCode(SqlTypes.JSON)
@Column(columnDefinition = "jsonb", nullable = false)
    private JsonNode payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutboxStatus status;

    @Column(nullable = false)
    private Integer retryCount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime publishedAt;

    @Column(length = 2000)
    private String lastError;

    public OutboxMessage() {
    }

    // =======================
    // Getters
    // =======================

    public Long getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public AggregateType getAggregateType() {
        return aggregateType;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getLastError() {
        return lastError;
    }

    public LocalDateTime getNextRetryAt(){
        return nextRetryAt;
    }

    public LocalDateTime getProcessingStartedAt(){
        return processingStartedAt;
    }

    public String getProcessingOwner(){
        return processingOwner;
    }

    // =======================
    // Setters
    // =======================

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public void setAggregateType(AggregateType aggregateType) {
        this.aggregateType = aggregateType;
    }

    public void setAggregateId(Long aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public void setStatus(OutboxStatus status) {
        this.status = status;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public void setNextRetryAt(LocalDateTime nextRetryAt){
        this.nextRetryAt = nextRetryAt;
    }

    public void setProcessingStartedAt(LocalDateTime process){
        this.processingStartedAt = process;
    }

    public void setProcessingOwner(String processOwner){
        this.processingOwner = processOwner;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}