package com.bank.core.account.service;

import com.bank.core.account.entity.Account;
import com.bank.core.account.repository.AccountRepository;
import com.bank.core.account.dto.CreateAccountRequest;
import com.bank.core.user.entity.User;
import com.bank.core.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.bank.core.ledger_entry.service.LedgerPort;
//import java.util.UUID; 

@Service
public class AccountService implements AccountPort {

    private final AccountRepository accountRepo;
    private final UserRepository userRepo;
     private final LedgerPort ledgerPort;

    public AccountService(AccountRepository accountRepo, UserRepository userRepo, LedgerPort port) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.ledgerPort = port;
    }

    
    @Override
    public Optional<Account> findByAccountNumberForUpdate(String accountNumber) {
    return accountRepo.findByAccountNumberForUpdate(accountNumber);
}

    public Account create(CreateAccountRequest request) { 

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber());

        return accountRepo.save(account);
    }


    public Double getBalance(Long accountId){
            return ledgerPort.getBalance(accountId);
    }

    private String generateAccountNumber() {
        return String.valueOf((long)(Math.random() * 1_0000_0000));
    }
}
