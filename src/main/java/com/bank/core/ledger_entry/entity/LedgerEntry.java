package com.bank.core.ledger_entry.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private Double amount; // +credit, -debit

    private String type; // DEBIT, CREDIT

    private Long transactionId;

    private LocalDateTime createdAt = LocalDateTime.now();

        public void setType(String type){
        this.type = type;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public void setTransactionId(Long transId){
        transactionId = transId;
    }

    public void setAccountId(Long id){
         accountId = id;
    }


    // getters/setters
}
