package com.bank.core.outbox.publisher;

import com.bank.core.outbox.entity.OutboxMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {

    //private static final String TOPIC = "bank.transactions";
    @Value("${bank.kafka.topic.transactions}")
    private String transactionTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(OutboxMessage message) {

        kafkaTemplate.send(
                transactionTopic,
                message.getEventId().toString(),
                message.getPayload().toString()).join();


        System.out.println("Published event to Kafka: " + message.getEventId());
    }
}