package com.project.neha.controller;

import com.project.neha.dto.SkillRequest;
import com.project.neha.entity.Skill;
import com.project.neha.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public Skill createSkill(@Valid @RequestBody SkillRequest request) {
        return skillService.createSkill(request);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable UUID id) {
        return skillService.getSkillById(id);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(
            @PathVariable UUID id,
            @Valid @RequestBody SkillRequest request
    ) {
        return skillService.updateSkill(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable UUID id) {
        skillService.deleteSkill(id);
        return "Skill deleted successfully";
    }
}
