package com.project.neha.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PlacementStatRequest(
        UUID companyId,
        Integer year,
        Integer studentsSelected,
        BigDecimal avgPackage
) {}
