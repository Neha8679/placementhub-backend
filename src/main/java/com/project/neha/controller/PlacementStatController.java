package com.project.neha.controller;

import com.project.neha.dto.PlacementStatRequest;
import com.project.neha.dto.PlacementStatResponse;
import com.project.neha.service.PlacementStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/placement-stats")
@RequiredArgsConstructor
public class PlacementStatController {

    private final PlacementStatService statService;

    @PostMapping
    public PlacementStatResponse createStat(
            @RequestBody PlacementStatRequest request
    ) {
        return statService.createStat(request);
    }

    @GetMapping
    public List<PlacementStatResponse> getAllStats() {
        return statService.getAllStats();
    }

    @GetMapping("/company/{companyId}")
    public List<PlacementStatResponse> getStatsByCompany(
            @PathVariable UUID companyId
    ) {
        return statService.getStatsByCompany(companyId);
    }
}