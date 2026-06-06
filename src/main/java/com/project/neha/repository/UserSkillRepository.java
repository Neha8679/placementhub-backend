package com.project.neha.repository;

import com.project.neha.entity.UserSkill;
import com.project.neha.entity.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserSkillRepository
        extends JpaRepository<UserSkill, UserSkillId> {

    List<UserSkill> findByUser_Id(UUID userId);
}
