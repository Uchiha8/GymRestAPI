package com.epam.dto.request;

import jakarta.persistence.Column;

public record StatusRequest(
        @Column(nullable = false)
        String username,
        @Column(nullable = false)
        Boolean isActive) {
}
