package com.epam.dto.response;

import java.time.Duration;
import java.util.Date;

public record TraineeTrainingsResponse(
        String trainingName,
        Date trainingDate,
        String trainingType,
        Duration duration,
        String trainerUsername) {
}
