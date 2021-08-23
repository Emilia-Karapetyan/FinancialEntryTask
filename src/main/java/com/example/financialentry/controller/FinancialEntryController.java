package com.example.financialentry.controller;

import com.example.financialentry.service.FinancialEntryService;
import com.example.financialentry.service.dto.FinancialEntryDto;
import com.example.financialentry.response.ContentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/financial-entries")
public class FinancialEntryController {
    private final FinancialEntryService financialEntryService;

    public FinancialEntryController(FinancialEntryService financialEntryService) {
        this.financialEntryService = financialEntryService;
    }

    @GetMapping
    public ContentResponse<FinancialEntryDto> get(Pageable pageable) {
        Page<FinancialEntryDto> financialEntryDtoPage = financialEntryService.getFinancialEntry(pageable);
        return new ContentResponse<>(financialEntryDtoPage.getTotalElements(), financialEntryDtoPage.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialEntryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(financialEntryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<FinancialEntryDto> create(@RequestBody FinancialEntryDto financialEntryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financialEntryService.createFinancialEntry(financialEntryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialEntryDto> update(@RequestBody FinancialEntryDto financialEntryDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(financialEntryService.update(id, financialEntryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        financialEntryService.deleteById(id);
        return ResponseEntity.ok().body("FinancialEntry deleted successfully");
    }

    @GetMapping("/calculate-balance")
    public ResponseEntity<Double> calculate() {
        return ResponseEntity.ok().body(financialEntryService.calculateBalance());
    }


}
