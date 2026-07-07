package com.bank.core.ledger_entry.service;
import com.bank.core.ledger_entry.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import com.bank.core.ledger_entry.entity.LedgerEntry;


@Service
public class LedgerService implements LedgerPort {

    private final LedgerRepository repo;

    public LedgerService(LedgerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void recordTransfer(Long fromId, Long toId, Double amount, Long txnId) {

        repo.save(build(fromId, -amount, "DEBIT", txnId));
        repo.save(build(toId, amount, "CREDIT", txnId));
    }

    @Override
    public void recordDeposit(Long accountId, Double amount, Long txnId) {

        repo.save(build(accountId, amount, "CREDIT", txnId));
    }

    @Override
    public void recordWithdrawal(Long accountId, Double amount, Long txnId) {

        repo.save(build(accountId, -amount, "DEBIT", txnId));
    }

    @Override
    public Double getBalance(Long accountId) {
        return repo.getBalance(accountId);
    }

    private LedgerEntry build(Long accountId, Double amount, String type, Long txnId) {
        LedgerEntry l = new LedgerEntry();
        l.setAccountId(accountId);
        l.setAmount(amount);
        l.setType(type);
        l.setTransactionId(txnId);
        return l;
    }
}
