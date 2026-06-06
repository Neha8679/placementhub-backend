package com.project.neha.dto;

import com.project.neha.enums.ApplicationStatus;

public record UpdateApplicationStatusRequest(
        ApplicationStatus status,
        String oaLink,
        String notes
) {
}
