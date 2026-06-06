package com.project.neha.controller;

import com.project.neha.dto.ResourceVoteRequest;
import com.project.neha.service.ResourceVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/resource-votes")
@RequiredArgsConstructor
public class ResourceVoteController {

    private final ResourceVoteService voteService;

    @PostMapping
    public String upvote(@RequestBody ResourceVoteRequest request) {
        return voteService.upvote(request);
    }

    @DeleteMapping
    public String removeVote(
            @RequestParam UUID userId,
            @RequestParam UUID resourceId
    ) {
        voteService.removeVote(userId, resourceId);
        return "Vote removed successfully";
    }

    @GetMapping("/count/{resourceId}")
    public long getVoteCount(@PathVariable UUID resourceId) {
        return voteService.getVoteCount(resourceId);
    }
}
