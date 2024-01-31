package com.epam.dto.request;

import jakarta.persistence.Column;

import java.time.Duration;
import java.util.Date;

public record TrainingRequest(
        @Column(nullable = false)
        String traineeUsername,
        @Column(nullable = false)
        String trainerUsername,
        @Column(nullable = false)
        String trainingName,
        @Column(nullable = false)
        Date trainingDate,
        @Column(nullable = false)
        Duration duration) {
}
