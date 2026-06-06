package com.project.neha.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVoteId implements Serializable {

    private UUID userId;

    private UUID resourceId;
}
