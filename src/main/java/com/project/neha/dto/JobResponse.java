package com.project.neha.dto;

import com.project.neha.enums.JobType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record JobResponse(

        UUID id,

        UUID companyId,

        String companyName,

        String title,

        BigDecimal packageLpa,

        JobType jobType,

        String eligibilityCriteria,

        LocalDate deadline,

        LocalDateTime createdAt

) {}
