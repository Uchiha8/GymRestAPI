package com.epam.dto.request;

import com.epam.domain.TrainingType;
import jakarta.persistence.Column;

public record UpdateTrainerRequest(
        @Column(nullable = false)
        String username,
        @Column(nullable = false)
        String firstName,
        @Column(nullable = false)
        String lastName,
        TrainingType trainingType,
        @Column(nullable = false)
        Boolean isActive) {
}
