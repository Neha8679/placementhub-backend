package com.project.neha.service;

import com.project.neha.dto.DashboardResponse;
import com.project.neha.enums.ApplicationStatus;
import com.project.neha.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ApplicationRepository applicationRepository;

    public DashboardResponse getDashboardStats() {

        return new DashboardResponse(
                applicationRepository.count(),
                applicationRepository.countByStatus(ApplicationStatus.SELECTED),
                applicationRepository.countByStatus(ApplicationStatus.REJECTED),
                applicationRepository.countByStatus(ApplicationStatus.INTERVIEW),
                applicationRepository.countByStatus(ApplicationStatus.OA_SCHEDULED),
                applicationRepository.countByStatus(ApplicationStatus.OA_CLEARED),
                applicationRepository.countByStatus(ApplicationStatus.APPLIED)
        );
    }
}
