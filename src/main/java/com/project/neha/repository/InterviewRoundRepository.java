package com.project.neha.repository;

import com.project.neha.entity.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterviewRoundRepository
        extends JpaRepository<InterviewRound, UUID> {

    List<InterviewRound> findByApplication_Id(UUID applicationId);
}
