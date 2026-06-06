package com.project.neha.repository;

import com.project.neha.entity.PlacementStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlacementStatRepository
        extends JpaRepository<PlacementStat, UUID> {

    List<PlacementStat> findByCompany_Id(UUID companyId);
}
