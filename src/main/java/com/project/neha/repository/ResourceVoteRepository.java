package com.project.neha.repository;

import com.project.neha.entity.ResourceVote;
import com.project.neha.entity.ResourceVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceVoteRepository
        extends JpaRepository<ResourceVote, ResourceVoteId> {

    long countByResource_Id(UUID resourceId);
}
