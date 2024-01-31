package com.epam.dto.request;

import com.epam.domain.TrainingType;
import jakarta.persistence.Column;

public record TrainerRegistrationRequest(
        @Column(nullable = false)
        String firstName,
        @Column(nullable = false)
        String lastName,
        @Column(nullable = false)
        TrainingType trainingType) {
}
