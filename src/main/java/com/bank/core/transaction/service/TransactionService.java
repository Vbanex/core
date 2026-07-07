package com.bank.core.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.account.entity.Account;
import com.bank.core.account.service.AccountPort;
import com.bank.core.transaction.dto.DepositRequest;
import com.bank.core.transaction.dto.TransferRequest;
import com.bank.core.transaction.dto.WithdrawalRequest;
import com.bank.core.transaction.entity.Transaction;
import com.bank.core.transaction.repository.TransactionRepository;
import com.bank.core.ledger_entry.service.LedgerPort;
import com.bank.core.outbox.service.OutboxPort;
import com.bank.core.shared.enums.AggregateType;
import com.bank.core.shared.enums.EventType;
import com.bank.core.shared.events.factory.DomainEventFactory;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final AccountPort accountPort;
    private final LedgerPort ledgerPort;
    private final OutboxPort outboxPort;

    public TransactionService(
            TransactionRepository transactionRepo,
            AccountPort accountPort,
            LedgerPort ledgerPort, OutboxPort outboxPort) {
        this.transactionRepo = transactionRepo;
        this.accountPort = accountPort;
        this.ledgerPort = ledgerPort;
        this.outboxPort = outboxPort;
    }

    // ================= TRANSFER =================
    @Transactional
    public void transfer(TransferRequest request) {

        if (transactionRepo.existsByReference(request.getReference())) {
    throw new RuntimeException("Duplicate transaction reference");
}

        Account from = accountPort.findByAccountNumberForUpdate(request.getFromAccount().trim())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account to = accountPort.findByAccountNumberForUpdate(request.getToAccount().trim())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Double amount = request.getAmount();

        // validate using ledger-derived balance
        Double balance = ledgerPort.getBalance(from.getId());
        if (balance < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        Transaction transaction = buildTxn(request.getReference(), "TRANSFER", from.getAccountNumber(), to.getAccountNumber(), amount);
        transaction = transactionRepo.save(transaction);

        ledgerPort.recordTransfer(from.getId(), to.getId(), amount, transaction.getId());

        outboxPort.publishEvent(AggregateType.TRANSACTION, transaction.getId(), EventType.MONEY_TRANSFERRED, DomainEventFactory.transfer(transaction));

        transaction.setStatus("COMPLETED");
    }

    // ================= DEPOSIT =================
    @Transactional
    public void deposit(DepositRequest request) {

        if (transactionRepo.existsByReference(request.getReference())) {
    throw new RuntimeException("Duplicate transaction reference");
}

        Account account = accountPort.findByAccountNumberForUpdate(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction transaction = buildTxn(request.getReference(), "DEPOSIT", null, account.getAccountNumber(), request.getAmount());
        transaction = transactionRepo.save(transaction);

        ledgerPort.recordDeposit(account.getId(), request.getAmount(), transaction.getId());

        outboxPort.publishEvent(AggregateType.TRANSACTION, transaction.getId(), EventType.MONEY_DEPOSITED, DomainEventFactory.deposit(transaction));

        transaction.setStatus("COMPLETED");
    }

    // ================= WITHDRAWAL =================
    @Transactional
    public void withdraw(WithdrawalRequest request) {

        if (transactionRepo.existsByReference(request.getReference())) return;

        Account account = accountPort.findByAccountNumberForUpdate(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Double balance = ledgerPort.getBalance(account.getId());
        if (balance < request.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        Transaction transaction = buildTxn(request.getReference(), "WITHDRAWAL", account.getAccountNumber(), null, request.getAmount());
        transaction = transactionRepo.save(transaction);

        ledgerPort.recordWithdrawal(account.getId(), request.getAmount(), transaction.getId());

                outboxPort.publishEvent(AggregateType.TRANSACTION, transaction.getId(), EventType.MONEY_WITHDRAWN, DomainEventFactory.withdrawal(transaction));

        transaction.setStatus("COMPLETED");
    }

    // ================= HELPER =================
    private Transaction buildTxn(String ref, String type, String from, String to, Double amount) {
        Transaction txn = new Transaction();
        txn.setReference(ref);
        txn.setType(type);
        txn.setFromAccount(from);
        txn.setToAccount(to);
        txn.setAmount(amount);
        txn.setStatus("PENDING");
        return txn;
    }
}
