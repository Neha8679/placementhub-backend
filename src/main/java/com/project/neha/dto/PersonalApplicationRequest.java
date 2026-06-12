package com.project.neha.dto;

import com.project.neha.enums.PersonalApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PersonalApplicationRequest(
        String companyName,
        String role,
        String source,
        LocalDate appliedDate,
        PersonalApplicationStatus status,
        LocalDate oaDate,
        LocalDate interviewDate,
        BigDecimal packageLpa,
        String notes
) {
}
