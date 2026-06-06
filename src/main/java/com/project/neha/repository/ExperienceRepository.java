package com.project.neha.repository;

import com.project.neha.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExperienceRepository extends JpaRepository<Experience, UUID> {

    List<Experience> findByCompany_Id(UUID companyId);

    List<Experience> findByUser_Id(UUID userId);
}
