package com.project.neha.repository;

import com.project.neha.entity.Resource;
import com.project.neha.enums.ResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository
        extends JpaRepository<Resource, UUID> {

    List<Resource> findByCategory(ResourceCategory category);

    List<Resource> findByCompany_Id(UUID companyId);
}
