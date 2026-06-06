package com.project.neha.controller;

import com.project.neha.dto.UserSkillRequest;
import com.project.neha.entity.UserSkill;
import com.project.neha.service.UserSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-skills")
@RequiredArgsConstructor
public class UserSkillController {

    private final UserSkillService userSkillService;

    @PostMapping
    public UserSkill addSkillToUser(@RequestBody UserSkillRequest request) {
        return userSkillService.addSkillToUser(request);
    }

    @GetMapping("/user/{userId}")
    public List<UserSkill> getSkillsByUser(@PathVariable UUID userId) {
        return userSkillService.getSkillsByUser(userId);
    }

    @DeleteMapping
    public String removeSkillFromUser(
            @RequestParam UUID userId,
            @RequestParam UUID skillId
    ) {
        userSkillService.removeSkillFromUser(userId, skillId);
        return "Skill removed from user successfully";
    }
}
