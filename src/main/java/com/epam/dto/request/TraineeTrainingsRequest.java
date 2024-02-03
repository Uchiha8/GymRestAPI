package com.epam.dto.request;

import jakarta.persistence.Column;

import java.util.Date;

public record TraineeTrainingsRequest(
        @Column(nullable = false)
        String username,
        Date periodFrom,
        Date periodTo,
        String trainingType,
        String trainerUsername) {
}
