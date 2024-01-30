package com.epam.dto.request;

import java.util.Date;

public record UpdateTraineeRequest(
        String username,
        String firstName,
        String lastName,
        Date dateOfBirth,
        String address,
        Boolean isActive) {
}
