package com.project.neha.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequest(
        @NotBlank String name,
        String sector,
        String oaPlatform
) {}
