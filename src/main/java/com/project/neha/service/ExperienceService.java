package com.project.neha.service;

import com.project.neha.dto.ExperienceRequest;
import com.project.neha.dto.ExperienceResponse;
import com.project.neha.entity.Company;
import com.project.neha.entity.Experience;
import com.project.neha.entity.User;
import com.project.neha.repository.CompanyRepository;
import com.project.neha.repository.ExperienceRepository;
import com.project.neha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private ExperienceResponse mapToResponse(Experience experience) {
        return new ExperienceResponse(
                experience.getId(),
                experience.getUser().getId(),
                experience.getUser().getName(),
                experience.getCompany().getId(),
                experience.getCompany().getName(),
                experience.getRoundType(),
                experience.getContent(),
                experience.getOutcome(),
                experience.getYear(),
                experience.getCreatedAt()
        );
    }

    public ExperienceResponse createExperience(ExperienceRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Experience experience = new Experience();
        experience.setUser(user);
        experience.setCompany(company);
        experience.setRoundType(request.roundType());
        experience.setContent(request.content());
        experience.setOutcome(request.outcome());
        experience.setYear(request.year());

        Experience savedExperience = experienceRepository.save(experience);

        return mapToResponse(savedExperience);
    }

    public List<ExperienceResponse> getAllExperiences() {
        return experienceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ExperienceResponse getExperienceById(UUID id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        return mapToResponse(experience);
    }

    public List<ExperienceResponse> getExperiencesByCompany(UUID companyId) {
        return experienceRepository.findByCompany_Id(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ExperienceResponse> getExperiencesByUser(UUID userId) {
        return experienceRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ExperienceResponse updateExperience(UUID id, ExperienceRequest request) {

        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        experience.setUser(user);
        experience.setCompany(company);
        experience.setRoundType(request.roundType());
        experience.setContent(request.content());
        experience.setOutcome(request.outcome());
        experience.setYear(request.year());

        Experience updatedExperience = experienceRepository.save(experience);

        return mapToResponse(updatedExperience);
    }

    public void deleteExperience(UUID id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        experienceRepository.delete(experience);
    }
}