package com.project.neha.dto;

import com.project.neha.enums.PersonalApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PersonalApplicationResponse(
        UUID id,
        String companyName,
        String role,
        String source,
        LocalDate appliedDate,
        PersonalApplicationStatus status,
        LocalDate oaDate,
        LocalDate interviewDate,
        BigDecimal packageLpa,
        String notes,
        LocalDateTime createdAt
) {
}