package com.project.neha.dto;

import com.project.neha.enums.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ApplicationResponse(
        UUID id,
        UUID userId,
        String userName,
        UUID jobId,
        String jobTitle,
        String companyName,
        ApplicationStatus status,
        LocalDate appliedOn,
        String oaLink,
        String notes,
        LocalDateTime updatedAt
) {}
