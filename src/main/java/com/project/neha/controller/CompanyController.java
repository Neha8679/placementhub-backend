package com.project.neha.controller;

import com.project.neha.dto.CompanyRequest;
import com.project.neha.dto.CompanyResponse;
import com.project.neha.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public CompanyResponse createCompany(
            @Valid @RequestBody CompanyRequest request
    ) {
        return companyService.createCompany(request);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable UUID id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyRequest request
    ) {
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
        return "Company deleted successfully";
    }
}