package com.example.financialentry.mapper;

import com.example.financialentry.model.FinancialEntry;
import com.example.financialentry.service.dto.FinancialEntryDto;
import org.springframework.stereotype.Component;

@Component
public class FinancialEntryMapper {
    public FinancialEntry toEntity(FinancialEntryDto financialEntryDto) {
        FinancialEntry financialEntry = new FinancialEntry();
        financialEntry.setBalance(financialEntryDto.getBalance());
        financialEntry.setType(financialEntryDto.getType());
        return financialEntry;
    }

    public FinancialEntryDto toDto(FinancialEntry financialEntry) {
        FinancialEntryDto financialEntryDto = new FinancialEntryDto();
        financialEntryDto.setId(financialEntry.getId());
        financialEntryDto.setBalance(financialEntry.getBalance());
        financialEntryDto.setType(financialEntry.getType());
        return financialEntryDto;
    }


}
