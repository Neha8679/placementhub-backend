package com.project.neha.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resource_votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVote {

    @EmbeddedId
    private ResourceVoteId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
