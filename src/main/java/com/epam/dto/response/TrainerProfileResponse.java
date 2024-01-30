package com.epam.dto.response;

import com.epam.domain.TrainingType;

import java.util.List;

public record TrainerProfileResponse(
        String firstName,
        String lastName,
        TrainingType trainingType,
        Boolean isActive,
        List<TraineeList> traineeLists) {
}
