package com.project.neha.service;

import com.project.neha.dto.ResourceVoteRequest;
import com.project.neha.entity.*;
import com.project.neha.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceVoteService {

    private final ResourceVoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public String upvote(ResourceVoteRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resource resource = resourceRepository.findById(request.resourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        ResourceVoteId id = new ResourceVoteId(
                request.userId(),
                request.resourceId()
        );

        if (voteRepository.existsById(id)) {
            throw new RuntimeException("User already voted this resource");
        }

        ResourceVote vote = new ResourceVote();
        vote.setId(id);
        vote.setUser(user);
        vote.setResource(resource);

        voteRepository.save(vote);

        return "Resource upvoted successfully";
    }

    public void removeVote(UUID userId, UUID resourceId) {

        ResourceVoteId id = new ResourceVoteId(userId, resourceId);

        ResourceVote vote = voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found"));

        voteRepository.delete(vote);
    }

    public long getVoteCount(UUID resourceId) {
        return voteRepository.countByResource_Id(resourceId);
    }
}
