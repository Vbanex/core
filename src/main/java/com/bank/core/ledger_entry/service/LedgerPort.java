package com.bank.core.ledger_entry.service;

public interface LedgerPort {

    void recordTransfer(Long fromId, Long toId, Double amount, Long txnId);

    void recordDeposit(Long accountId, Double amount, Long txnId);

    void recordWithdrawal(Long accountId, Double amount, Long txnId);

    Double getBalance(Long accountId);
}
