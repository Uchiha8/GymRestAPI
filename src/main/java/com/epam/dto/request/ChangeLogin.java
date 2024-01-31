package com.epam.dto.request;

import jakarta.persistence.Column;

public record ChangeLogin(
        @Column(nullable = false)
        String username,
        @Column(nullable = false)
        String oldPassword,
        @Column(nullable = false)
        String newPassword) {
}
