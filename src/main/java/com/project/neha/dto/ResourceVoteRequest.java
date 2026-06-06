package com.project.neha.dto;

import java.util.UUID;

public record ResourceVoteRequest(
        UUID userId,
        UUID resourceId
) {}
