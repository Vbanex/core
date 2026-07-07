package com.bank.core.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepositRequest {
   @NotBlank
    private String reference;
   @NotBlank
    private String accountNumber;
   @NotNull
    private Double amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

public void setReference(String refernece){
    this.reference = refernece;
}

public String getReference(){
    return reference;
}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
