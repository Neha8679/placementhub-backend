package com.project.neha.service;

import com.project.neha.dto.PersonalApplicationRequest;
import com.project.neha.dto.PersonalApplicationResponse;
import com.project.neha.entity.PersonalApplication;
import com.project.neha.entity.User;
import com.project.neha.repository.PersonalApplicationRepository;
import com.project.neha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalApplicationService {

    private final PersonalApplicationRepository repository;
    private final UserRepository userRepository;

    public PersonalApplicationResponse create(
            PersonalApplicationRequest request,
            String email
    ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PersonalApplication application = PersonalApplication.builder()
                .user(user)
                .companyName(request.companyName())
                .role(request.role())
                .source(request.source())
                .appliedDate(request.appliedDate())
                .status(request.status())
                .oaDate(request.oaDate())
                .interviewDate(request.interviewDate())
                .packageLpa(request.packageLpa())
                .notes(request.notes())
                .build();

        return mapToResponse(repository.save(application));
    }

    public List<PersonalApplicationResponse> getMyApplications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return repository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PersonalApplicationResponse update(
            UUID id,
            PersonalApplicationRequest request,
            String email
    ) {
        PersonalApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You cannot update this application");
        }

        application.setCompanyName(request.companyName());
        application.setRole(request.role());
        application.setSource(request.source());
        application.setAppliedDate(request.appliedDate());
        application.setStatus(request.status());
        application.setOaDate(request.oaDate());
        application.setInterviewDate(request.interviewDate());
        application.setPackageLpa(request.packageLpa());
        application.setNotes(request.notes());

        return mapToResponse(repository.save(application));
    }

    public void delete(UUID id, String email) {
        PersonalApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You cannot delete this application");
        }

        repository.delete(application);
    }

    private PersonalApplicationResponse mapToResponse(
            PersonalApplication application
    ) {
        return new PersonalApplicationResponse(
                application.getId(),
                application.getCompanyName(),
                application.getRole(),
                application.getSource(),
                application.getAppliedDate(),
                application.getStatus(),
                application.getOaDate(),
                application.getInterviewDate(),
                application.getPackageLpa(),
                application.getNotes(),
                application.getCreatedAt()
        );
    }
}
