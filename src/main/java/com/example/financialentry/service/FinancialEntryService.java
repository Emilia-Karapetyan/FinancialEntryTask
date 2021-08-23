package com.example.financialentry.service;

import com.example.financialentry.exception.BadRequestException;
import com.example.financialentry.exception.NotFoundException;
import com.example.financialentry.mapper.FinancialEntryMapper;
import com.example.financialentry.model.FinancialEntry;
import com.example.financialentry.repository.FinancialEntryRepository;
import com.example.financialentry.service.dto.FinancialEntryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FinancialEntryService {
    private final FinancialEntryMapper financialEntryMapper;
    private final FinancialEntryRepository financialEntryRepository;

    public FinancialEntryService(FinancialEntryMapper financialEntryMapper, FinancialEntryRepository financialEntryRepository) {
        this.financialEntryMapper = financialEntryMapper;
        this.financialEntryRepository = financialEntryRepository;
    }

    public FinancialEntryDto createFinancialEntry(FinancialEntryDto financialEntryDto) {
        if (financialEntryDto.getType() == null) {
            throw new BadRequestException("Type can not be null");
        }
        FinancialEntry financialEntry = financialEntryMapper.toEntity(financialEntryDto);
        return financialEntryMapper.toDto(financialEntryRepository.save(financialEntry));
    }

    public Page<FinancialEntryDto> getFinancialEntry(Pageable pageable) {
        return financialEntryRepository.findAll(pageable).map(financialEntryMapper::toDto);
    }

    public FinancialEntryDto getById(Long id) {
        FinancialEntry financialEntry = financialEntryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Entry which id %d not found", id)));
        return financialEntryMapper.toDto(financialEntry);
    }

    public FinancialEntryDto update(Long id, FinancialEntryDto financialEntryDto) {
        FinancialEntry financialEntry = financialEntryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Entry which id %d not found", id)));

        if (financialEntryDto.getType() == null) {
            throw new BadRequestException("Type can not be null");
        }

        financialEntry.setBalance(financialEntryDto.getBalance());
        financialEntry.setType(financialEntryDto.getType());
        return financialEntryMapper.toDto(financialEntryRepository.save(financialEntry));
    }

    public void deleteById(Long id) {
        if (!financialEntryRepository.existsById(id)) {
            throw new NotFoundException("Entry doesn't exist");
        }
        financialEntryRepository.deleteById(id);
    }

    public double calculateBalance() {
        return financialEntryRepository.calculateDebit() - financialEntryRepository.calculateCredit();
    }
}
