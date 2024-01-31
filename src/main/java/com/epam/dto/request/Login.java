package com.epam.dto.request;

import jakarta.persistence.Column;

public record Login(
        @Column(nullable = false)
        String username,
        @Column(nullable = false)
        String password) {
}
