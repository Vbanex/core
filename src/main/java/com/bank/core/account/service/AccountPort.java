package com.bank.core.account.service;

import com.bank.core.account.entity.Account;
import java.util.Optional;

public interface AccountPort {
Optional<Account> findByAccountNumberForUpdate(String accountNumber);    
} 
