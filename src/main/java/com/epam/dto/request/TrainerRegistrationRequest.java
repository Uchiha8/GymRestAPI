package com.epam.dto.request;

import com.epam.domain.TrainingType;

public record TrainerRegistrationRequest(String firstName, String lastName, TrainingType trainingType) {
}
