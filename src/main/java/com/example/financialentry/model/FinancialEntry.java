package com.example.financialentry.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "FinancialEntry")
@Table(name = "financial_entry")
public class FinancialEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "balance")
    private double balance;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
}
