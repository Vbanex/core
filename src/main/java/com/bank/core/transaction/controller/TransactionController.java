package com.bank.core.transaction.controller;

import com.bank.core.transaction.dto.DepositRequest;
import com.bank.core.transaction.dto.TransferRequest;
import com.bank.core.transaction.dto.WithdrawalRequest;
import com.bank.core.transaction.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // ================= DEPOSIT =================
    @PostMapping("/deposit")
    public String deposit(@Valid @RequestBody DepositRequest request) {
        service.deposit(request);
        return "Deposit successful";
    }

    // ================= TRANSFER =================
    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequest request) {
                   if (request.getFromAccount().equals(request.getToAccount())) {
        throw new IllegalArgumentException("Cannot transfer to same account");
    }
        service.transfer(request);
        return "Transfer successful";
    }

    // ================= WITHDRAW =================
    @PostMapping("/withdraw")
    public String withdraw(@Valid @RequestBody WithdrawalRequest request) {
        service.withdraw(request);
        return "Withdrawal successful";
    }
}
