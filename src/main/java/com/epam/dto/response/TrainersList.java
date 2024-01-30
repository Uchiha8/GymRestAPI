package com.epam.dto.response;

import com.epam.domain.TrainingType;

public record TrainersList(
        String username,
        String firstName,
        String lastName,
        TrainingType trainingType) {
}
