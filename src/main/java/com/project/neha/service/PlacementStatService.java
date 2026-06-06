package com.project.neha.service;

import com.project.neha.dto.PlacementStatRequest;
import com.project.neha.dto.PlacementStatResponse;
import com.project.neha.entity.Company;
import com.project.neha.entity.PlacementStat;
import com.project.neha.repository.CompanyRepository;
import com.project.neha.repository.PlacementStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlacementStatService {

    private final PlacementStatRepository statRepository;
    private final CompanyRepository companyRepository;

    private PlacementStatResponse mapToResponse(PlacementStat stat) {
        return new PlacementStatResponse(
                stat.getId(),
                stat.getCompany().getId(),
                stat.getCompany().getName(),
                stat.getYear(),
                stat.getStudentsSelected(),
                stat.getAvgPackage()
        );
    }

    public PlacementStatResponse createStat(PlacementStatRequest request) {

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        PlacementStat stat = new PlacementStat();
        stat.setCompany(company);
        stat.setYear(request.year());
        stat.setStudentsSelected(request.studentsSelected());
        stat.setAvgPackage(request.avgPackage());

        PlacementStat savedStat = statRepository.save(stat);

        return mapToResponse(savedStat);
    }

    public List<PlacementStatResponse> getAllStats() {
        return statRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<PlacementStatResponse> getStatsByCompany(UUID companyId) {
        return statRepository.findByCompany_Id(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}