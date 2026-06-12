package com.project.neha.repository;

import com.project.neha.entity.PersonalApplication;
import com.project.neha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonalApplicationRepository
        extends JpaRepository<PersonalApplication, UUID> {

    List<PersonalApplication> findByUser(User user);
}
