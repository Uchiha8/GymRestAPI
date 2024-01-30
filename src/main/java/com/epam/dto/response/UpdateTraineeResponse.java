package com.epam.dto.response;

import java.util.Date;
import java.util.List;

public record UpdateTraineeResponse(
        String username,
        String firstName,
        String lastName,
        Date dateOfBirth,
        String address,
        Boolean isActive,
        List<TrainersList> trainersList) {
}
