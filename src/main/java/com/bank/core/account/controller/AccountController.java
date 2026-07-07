package com.bank.core.account.controller;

import com.bank.core.account.entity.Account;
import com.bank.core.account.service.AccountService;
import com.bank.core.outbox.worker.OutboxWorker;
import com.bank.core.account.dto.CreateAccountRequest;
//import com.bank.core.transaction.dto.DepositRequest;
//import com.bank.core.transaction.dto.TransferRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;
    private final OutboxWorker worker;

    public AccountController(AccountService service, OutboxWorker worker) {
        this.service = service;
        this.worker = worker;
    }

    @PostMapping
    public Account createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return service.create(request);
    }

    @GetMapping("/balance")
    public Double accountBalance(@Valid @RequestParam("accountId") Long accountId){
       return service.getBalance(accountId);
    }

    @GetMapping("/worker")
    public void Work(){
      worker.processBatch();
    }


    

}