package com.project.neha.repository;

import com.project.neha.entity.Application;
import com.project.neha.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository
        extends JpaRepository<Application, UUID> {

    boolean existsByUser_IdAndJob_Id(UUID userId, UUID jobId);
    List<Application> findByUser_Id(UUID userId);
    long countByStatus(ApplicationStatus status);
}