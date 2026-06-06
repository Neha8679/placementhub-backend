package com.project.neha.dto;

import java.util.UUID;

public record UserSkillRequest(
        UUID userId,
        UUID skillId
) {
}
