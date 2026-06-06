package com.project.neha.dto;

import com.project.neha.enums.RoundType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExperienceResponse(
        UUID id,
        UUID userId,
        String userName,
        UUID companyId,
        String companyName,
        RoundType roundType,
        String content,
        String outcome,
        Integer year,
        LocalDateTime createdAt
) {
}
