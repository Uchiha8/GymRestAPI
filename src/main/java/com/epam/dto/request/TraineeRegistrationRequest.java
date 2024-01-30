package com.epam.dto.request;

import jakarta.persistence.Column;

import java.util.Date;

public record TraineeRegistrationRequest(
        @Column(nullable = false)
        String firstName,
        @Column(nullable = false)
        String lastName,
        Date dateOfBirth,
        String address) {
}
