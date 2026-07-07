package com.bank.core.transaction.dto;

public class WithdrawalRequest {
        private String reference;
    private String accountNumber;
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
