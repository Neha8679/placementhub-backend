package com.project.neha.dto;

import com.project.neha.enums.ApplicationStatus;

public record ApplicationStatusRequest(

        ApplicationStatus status
) {
}
