package com.project.neha.service;

import com.project.neha.dto.ApplicationRequest;
import com.project.neha.dto.ApplicationResponse;
import com.project.neha.dto.ApplicationStatusRequest;
import com.project.neha.entity.Application;
import com.project.neha.entity.Job;
import com.project.neha.entity.User;
import com.project.neha.repository.ApplicationRepository;
import com.project.neha.repository.JobRepository;
import com.project.neha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private ApplicationResponse mapToResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getUser().getId(),
                application.getUser().getName(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getJob().getCompany().getName(),
                application.getStatus(),
                application.getAppliedOn(),
                application.getOaLink(),
                application.getNotes(),
                application.getUpdatedAt()
        );
    }
    public ApplicationResponse apply(ApplicationRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(request.jobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.existsByUser_IdAndJob_Id(
                request.userId(), request.jobId())) {
            throw new RuntimeException("User already applied to this job");
        }

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setOaLink(request.oaLink());
        application.setNotes(request.notes());

        Application savedApplication =
                applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ApplicationResponse getApplicationById(UUID id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        return mapToResponse(application);
    }
    public ApplicationResponse updateStatus(
            UUID id,
            ApplicationStatusRequest request
    ) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(request.status());

        Application updatedApplication =
                applicationRepository.save(application);

        return mapToResponse(updatedApplication);
    }
    public void deleteApplication(UUID id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        applicationRepository.delete(application);
    }
    public ApplicationResponse applyToJob(UUID jobId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (applicationRepository.existsByUser_IdAndJob_Id(
                user.getId(),
                job.getId()
        )) {
            throw new RuntimeException("You have already applied to this job");
        }

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        Application savedApplication = applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }
    public List<ApplicationResponse> getMyApplications(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByUser_Id(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}
