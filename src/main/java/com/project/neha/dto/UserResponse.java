package com.project.neha.dto;

import com.project.neha.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String college,
        String branch,
        Integer graduationYear,
        String resumeUrl,
        Role role,
        LocalDateTime createdAt
) {}
