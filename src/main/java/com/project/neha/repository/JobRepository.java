package com.project.neha.repository;

import com.project.neha.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface JobRepository extends JpaRepository<Job, UUID> {
    Page<Job> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );
}
