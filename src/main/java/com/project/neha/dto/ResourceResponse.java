package com.project.neha.dto;

import com.project.neha.enums.ResourceCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResourceResponse(
        UUID id,
        UUID userId,
        String userName,
        UUID companyId,
        String companyName,
        String title,
        String url,
        ResourceCategory category,
        LocalDateTime createdAt
) {
}
