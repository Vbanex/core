package com.bank.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "outbox")
public class OutboxProperties {

    private int batchSize = 100;

    private int maxRetries = 10;

    private int pollInterval = 5000;

    private int initialBackoffSeconds = 5;

    // Getters

    public int getBatchSize() {
        return batchSize;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public int getPollInterval() {
        return pollInterval;
    }

    public int getInitialBackoffSeconds() {
        return initialBackoffSeconds;
    }

    // Setters

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void setPollInterval(int pollInterval) {
        this.pollInterval = pollInterval;
    }

    public void setInitialBackoffSeconds(int initialBackoffSeconds) {
        this.initialBackoffSeconds = initialBackoffSeconds;
    }
}
