package com.bank.core.transaction.dto;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.NotBlank;
public class TransferRequest {

    @NotBlank
    private String reference;

   @NotBlank
    private String fromAccount;

    @NotBlank
    private String toAccount;

    @NotNull
    private Double amount;

    public void setReference(String reference){

        this.reference = reference;
    }

    public String getReference(){
        return reference;
    }


    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
