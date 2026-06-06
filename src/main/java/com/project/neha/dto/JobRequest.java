package com.project.neha.dto;

import com.project.neha.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record JobRequest(
        @NotNull UUID companyId,
        @NotBlank String title,
        BigDecimal packageLpa,
        JobType jobType,
        String eligibilityCriteria,
        LocalDate deadline
) {}
