package com.project.neha.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PlacementStatResponse(
        UUID id,
        UUID companyId,
        String companyName,
        Integer year,
        Integer studentsSelected,
        BigDecimal avgPackage
) {
}
