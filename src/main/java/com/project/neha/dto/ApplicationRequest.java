package com.project.neha.dto;

import java.util.UUID;

public record ApplicationRequest(

        UUID userId,
        UUID jobId,
        String oaLink,
        String notes
) {
}
