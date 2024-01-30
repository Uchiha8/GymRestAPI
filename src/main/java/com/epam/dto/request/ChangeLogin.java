package com.epam.dto.request;

public record ChangeLogin(String username, String oldPassword, String newPassword) {
}
