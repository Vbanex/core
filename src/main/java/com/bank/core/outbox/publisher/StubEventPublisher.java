package com.bank.core.outbox.publisher;

import com.bank.core.outbox.entity.OutboxMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Component;

//@Component("stubPublisher")
public class StubEventPublisher
        implements EventPublisher {

    private final ObjectMapper mapper;

    public StubEventPublisher(ObjectMapper mapper) {
        this.mapper = mapper;
    }


@Override
public void publish(OutboxMessage message) {

    try {

        System.out.println();

        System.out.println("======================================");

        System.out.println("Publishing Event");

        System.out.println("Event Id : " +
                message.getEventId());

        System.out.println("Type     : " +
                message.getEventType());

        System.out.println("Payload  :");

        System.out.println(

                mapper.writerWithDefaultPrettyPrinter()

                        .writeValueAsString(
                                message.getPayload())

        );

        System.out.println("======================================");

    }
    catch (Exception ex) {

        throw new RuntimeException(ex);

    }

}

}
