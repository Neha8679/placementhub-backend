package com.project.neha.service;

import com.project.neha.dto.UserSkillRequest;
import com.project.neha.entity.*;
import com.project.neha.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public UserSkill addSkillToUser(UserSkillRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = skillRepository.findById(request.skillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        UserSkillId id = new UserSkillId(
                request.userId(),
                request.skillId()
        );

        if (userSkillRepository.existsById(id)) {
            throw new RuntimeException("Skill already added to user");
        }

        UserSkill userSkill = new UserSkill();
        userSkill.setId(id);
        userSkill.setUser(user);
        userSkill.setSkill(skill);

        return userSkillRepository.save(userSkill);
    }

    public List<UserSkill> getSkillsByUser(UUID userId) {
        return userSkillRepository.findByUser_Id(userId);
    }

    public void removeSkillFromUser(UUID userId, UUID skillId) {

        UserSkillId id = new UserSkillId(userId, skillId);

        UserSkill userSkill = userSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User skill not found"));

        userSkillRepository.delete(userSkill);
    }
}
