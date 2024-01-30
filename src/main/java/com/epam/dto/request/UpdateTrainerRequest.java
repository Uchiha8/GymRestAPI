package com.epam.dto.request;

import com.epam.domain.TrainingType;

public record UpdateTrainerRequest(
        String username,
        String firstName,
        String lastName,
        TrainingType trainingType,
        Boolean isActive) {
}
