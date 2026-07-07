package com.bank.core.transaction.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String reference; // idempotency key

    private String type; // DEPOSIT, TRANSFER, WITHDRAWAL

    private String fromAccount; // nullable for deposit
    private String toAccount;   // nullable for withdrawal

    private Double amount;

    private String status; // PENDING, COMPLETED, FAILED

    private LocalDateTime createdAt = LocalDateTime.now();

    public void setType(String type){
        this.type = type;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public void setToAccount(String acc){
        toAccount = acc;
    }

    public void setFromAccount(String acc){
        fromAccount = acc;
    }

    public void setReference(String referenece){
        this.reference = referenece;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Long getId(){
        return id;
    }
    public String getFromAccount(){
    return this.fromAccount;
   }

   public String getToAccount(){
    return this.toAccount;
   }

   public String getReference(){
    return this.reference;
   }

   public Double getAmount(){
    return this.amount;
   }


}
