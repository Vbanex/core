package com.bank.core.outbox.worker;

import com.bank.core.outbox.entity.OutboxMessage;
import com.bank.core.outbox.publisher.EventPublisher;
import com.bank.core.outbox.service.OutboxClaimService;
import com.bank.core.outbox.service.OutboxStateService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class OutboxWorker {

    private final OutboxClaimService claimService;
    private final EventPublisher publisher;
    private final OutboxStateService stateService;

    private final UUID workerId = UUID.randomUUID();

    public OutboxWorker(
            OutboxClaimService claimService,
            EventPublisher publisher, OutboxStateService service) {

        this.claimService = claimService;
        this.publisher = publisher;
        this.stateService = service;
    }

public void processBatch() {

    System.out.println("===== Worker Started =====");

    List<OutboxMessage> batch =
            claimService.claimPendingEvents(workerId);

    System.out.println("Batch Size = " + batch.size());

    if (batch.isEmpty()) {
        System.out.println("No messages found.");
        return;
    }

    for (OutboxMessage message : batch) {

        System.out.println("--------------------------------");
        System.out.println("Processing: " + message.getEventId());

        try {

            System.out.println("Before publish");

            publisher.publish(message);

            System.out.println("After publish");

            stateService.markSuccess(message);

            System.out.println("After markSuccess");

        }
        catch (Exception ex) {

            System.out.println("Publisher Exception:");
            ex.printStackTrace();

            try {

                stateService.markFailure(message, ex);

                System.out.println("After markFailure");

            }
            catch (Exception inner) {

                System.out.println("markFailure Exception:");

                inner.printStackTrace();

            }

        }

    }

    System.out.println("===== Worker Finished =====");
}


/*
 public void processBatch() {

    List<OutboxMessage> batch =
            claimService.claimPendingEvents(workerId);

    if (batch.isEmpty()) {
        return;
    }

    for (OutboxMessage message : batch) {

        try {

            publisher.publish(message);

            stateService.markSuccess(message);

            System.out.println(
                    "Published -> " +
                    message.getEventId());

        }
        catch (Exception ex) {

            stateService.markFailure(
                    message,
                    ex);

            System.out.println(
                    "Publish failed -> " +
                    message.getEventId());

        }

    }

}
*/


}