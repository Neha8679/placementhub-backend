package com.project.neha.controller;

import com.project.neha.dto.ExperienceRequest;
import com.project.neha.dto.ExperienceResponse;
import com.project.neha.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ExperienceResponse createExperience(@RequestBody ExperienceRequest request) {
        return experienceService.createExperience(request);
    }

    @GetMapping
    public List<ExperienceResponse> getAllExperiences() {
        return experienceService.getAllExperiences();
    }

    @GetMapping("/{id}")
    public ExperienceResponse getExperienceById(@PathVariable UUID id) {
        return experienceService.getExperienceById(id);
    }

    @GetMapping("/company/{companyId}")
    public List<ExperienceResponse> getExperiencesByCompany(@PathVariable UUID companyId) {
        return experienceService.getExperiencesByCompany(companyId);
    }

    @GetMapping("/user/{userId}")
    public List<ExperienceResponse> getExperiencesByUser(@PathVariable UUID userId) {
        return experienceService.getExperiencesByUser(userId);
    }

    @PutMapping("/{id}")
    public ExperienceResponse updateExperience(
            @PathVariable UUID id,
            @RequestBody ExperienceRequest request
    ) {
        return experienceService.updateExperience(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteExperience(@PathVariable UUID id) {
        experienceService.deleteExperience(id);
        return "Experience deleted successfully";
    }
}