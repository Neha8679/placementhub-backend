package com.project.neha.dto;

import com.project.neha.enums.RoundType;

import java.util.UUID;

public record ExperienceRequest(
        UUID userId,
        UUID companyId,
        RoundType roundType,
        String content,
        String outcome,
        Integer year
) {}
