package com.project.neha.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        String sector,
        String oaPlatform,
        LocalDateTime createdAt
) {
}
