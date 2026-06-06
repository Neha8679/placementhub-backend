package com.project.neha.service;

import com.project.neha.dto.CompanyRequest;
import com.project.neha.dto.CompanyResponse;
import com.project.neha.entity.Company;
import com.project.neha.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private CompanyResponse mapToResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getSector(),
                company.getOaPlatform(),
                company.getCreatedAt()
        );
    }

    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.name());
        company.setSector(request.sector());
        company.setOaPlatform(request.oaPlatform());

        Company savedCompany = companyRepository.save(company);

        return mapToResponse(savedCompany);
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToResponse(company);
    }

    public CompanyResponse updateCompany(UUID id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(request.name());
        company.setSector(request.sector());
        company.setOaPlatform(request.oaPlatform());

        Company updatedCompany = companyRepository.save(company);

        return mapToResponse(updatedCompany);
    }

    public void deleteCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.delete(company);
    }
}