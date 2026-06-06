package com.project.neha.service;

import com.project.neha.dto.JobRequest;
import com.project.neha.dto.JobResponse;
import com.project.neha.dto.PageResponse;
import com.project.neha.entity.Company;
import com.project.neha.entity.Job;
import com.project.neha.repository.CompanyRepository;
import com.project.neha.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import com.project.neha.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    private JobResponse mapToResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getTitle(),
                job.getPackageLpa(),
                job.getJobType(),
                job.getEligibilityCriteria(),
                job.getDeadline(),
                job.getCreatedAt()
        );
    }

    public JobResponse createJob(JobRequest request) {

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Job job = new Job();
        job.setCompany(company);
        job.setTitle(request.title());
        job.setPackageLpa(request.packageLpa());
        job.setJobType(request.jobType());
        job.setEligibilityCriteria(request.eligibilityCriteria());
        job.setDeadline(request.deadline());

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public JobResponse getJobById(UUID id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    public JobResponse updateJob(UUID id, JobRequest request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        job.setCompany(company);
        job.setTitle(request.title());
        job.setPackageLpa(request.packageLpa());
        job.setJobType(request.jobType());
        job.setEligibilityCriteria(request.eligibilityCriteria());
        job.setDeadline(request.deadline());

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    public void deleteJob(UUID id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jobRepository.delete(job);
    }
    public PageResponse<JobResponse> searchJobs(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<JobResponse> jobPage = jobRepository
                .findByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToResponse);

        return new PageResponse<>(
                jobPage.getContent(),
                jobPage.getNumber(),
                jobPage.getSize(),
                jobPage.getTotalElements(),
                jobPage.getTotalPages(),
                jobPage.isLast()
        );
    }
    public PageResponse<JobResponse> getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<JobResponse> jobPage = jobRepository.findAll(pageable)
                .map(this::mapToResponse);

        return new PageResponse<>(
                jobPage.getContent(),
                jobPage.getNumber(),
                jobPage.getSize(),
                jobPage.getTotalElements(),
                jobPage.getTotalPages(),
                jobPage.isLast()
        );
    }
}
