package com.project.neha.dto;

import jakarta.validation.constraints.NotBlank;

public record SkillRequest(
        @NotBlank String name
) {}
