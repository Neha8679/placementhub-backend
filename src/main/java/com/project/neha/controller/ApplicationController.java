package com.project.neha.controller;

import com.project.neha.dto.ApplicationRequest;
import com.project.neha.dto.ApplicationResponse;
import com.project.neha.dto.ApplicationStatusRequest;
import com.project.neha.service.ApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationResponse apply(@RequestBody ApplicationRequest request) {
        return applicationService.apply(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable UUID id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}/status")
    public ApplicationResponse updateStatus(
            @PathVariable UUID id,
            @RequestBody ApplicationStatusRequest request
    ) {
        return applicationService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable UUID id) {
        applicationService.deleteApplication(id);
        return "Application deleted successfully";
    }
    @PostMapping("/apply/{jobId}")
    public ApplicationResponse applyToJob(
            @PathVariable UUID jobId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return applicationService.applyToJob(jobId, email);
    }
    @GetMapping("/my")
    public List<ApplicationResponse> getMyApplications(
            Authentication authentication
    ) {
        String email = authentication.getName();
        return applicationService.getMyApplications(email);
    }

}
