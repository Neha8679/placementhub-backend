package com.project.neha.dto;

import com.project.neha.enums.ResourceCategory;

import java.util.UUID;

public record ResourceRequest(

        UUID userId,
        UUID companyId,
        String title,
        String url,
        ResourceCategory category
) {
}
