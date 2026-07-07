package com.bank.core.shared.events.factory;

import com.bank.core.shared.events.*;
import com.bank.core.transaction.entity.Transaction;

public final class DomainEventFactory {

    private DomainEventFactory() {
    }

    public static MoneyTransferredEvent transfer(Transaction txn) {

        MoneyTransferredEvent event = new MoneyTransferredEvent();

        event.setTransactionId(txn.getId());

        event.setReference(txn.getReference());

        event.setFromAccount(txn.getFromAccount());

        event.setToAccount(txn.getToAccount());

        event.setAmount(txn.getAmount());

        return event;
    }

    public static MoneyDepositedEvent deposit(Transaction txn) {

        MoneyDepositedEvent event = new MoneyDepositedEvent();

        event.setTransactionId(txn.getId());

        event.setReference(txn.getReference());

        event.setAccountNumber(txn.getToAccount());

        event.setAmount(txn.getAmount());

        return event;
    }

    public static MoneyWithdrawnEvent withdrawal(Transaction txn) {

        MoneyWithdrawnEvent event = new MoneyWithdrawnEvent();

        event.setTransactionId(txn.getId());

        event.setReference(txn.getReference());

        event.setAccountNumber(txn.getFromAccount());

        event.setAmount(txn.getAmount());

        return event;
    }

}
