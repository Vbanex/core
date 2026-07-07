package com.bank.core.ledger_entry.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.core.ledger_entry.entity.LedgerEntry;
import org.springframework.data.jpa.repository.Query;


public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("SELECT COALESCE(SUM(l.amount), 0) FROM LedgerEntry l WHERE l.accountId = :accountId")
    Double getBalance(Long accountId);
}
