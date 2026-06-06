package com.project.neha.controller;

import com.project.neha.dto.JobRequest;
import com.project.neha.dto.JobResponse;
import com.project.neha.dto.PageResponse;
import com.project.neha.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public JobResponse createJob(@Valid @RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping
    public PageResponse<JobResponse> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return jobService.getAllJobs(page, size);
    }

    @GetMapping("/{id}")
    public JobResponse getJobById(@PathVariable UUID id) {
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public JobResponse updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody JobRequest request
    ) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable UUID id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }
    @GetMapping("/search")
    public PageResponse<JobResponse> searchJobs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return jobService.searchJobs(keyword, page, size);
    }
}