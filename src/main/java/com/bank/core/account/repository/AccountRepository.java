package com.bank.core.account.repository;

import com.bank.core.account.entity.Account;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

Optional<Account> findByAccountNumber(String accountNumber);

@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("select a from Account a where a.accountNumber = :accountNumber")
Optional<Account> findByAccountNumberForUpdate(String accountNumber);

}