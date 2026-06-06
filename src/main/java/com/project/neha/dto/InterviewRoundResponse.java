package com.project.neha.dto;

import com.project.neha.enums.RoundType;

import java.time.LocalDate;
import java.util.UUID;

public record InterviewRoundResponse(
        UUID id,
        UUID applicationId,
        Integer roundNumber,
        RoundType roundType,
        String outcome,
        String notes,
        LocalDate roundDate
) {
}
