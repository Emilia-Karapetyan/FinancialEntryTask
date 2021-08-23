package com.example.financialentry.repository;

import com.example.financialentry.model.FinancialEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialEntryRepository extends JpaRepository<FinancialEntry, Long> {
    @Query("SELECT SUM(f.balance) FROM FinancialEntry f WHERE f.type = 'CREDIT'")
    double calculateCredit();

    @Query("SELECT SUM(f.balance) FROM FinancialEntry f WHERE f.type = 'DEBIT'")
    double calculateDebit();
}
