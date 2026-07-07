package com.bank.core.transaction.repository;

import com.bank.core.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
       boolean existsByReference(String reference);
}
