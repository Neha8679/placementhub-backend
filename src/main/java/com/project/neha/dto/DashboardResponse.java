package com.project.neha.dto;

public record DashboardResponse(
        long totalApplications,
        long selected,
        long rejected,
        long interview,
        long oaScheduled,
        long oaCleared,
        long applied
) {
}
