
package com.project.neha.service;

import com.project.neha.dto.SkillRequest;
import com.project.neha.entity.Skill;
import com.project.neha.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill createSkill(SkillRequest request) {

        skillRepository.findByName(request.name())
                .ifPresent(skill -> {
                    throw new RuntimeException("Skill already exists");
                });

        Skill skill = new Skill();
        skill.setName(request.name());

        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(UUID id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public Skill updateSkill(UUID id, SkillRequest request) {

        Skill skill = getSkillById(id);
        skill.setName(request.name());

        return skillRepository.save(skill);
    }

    public void deleteSkill(UUID id) {
        Skill skill = getSkillById(id);
        skillRepository.delete(skill);
    }
}
