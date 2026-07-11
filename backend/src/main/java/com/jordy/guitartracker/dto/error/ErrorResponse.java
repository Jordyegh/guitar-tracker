package com.jordy.guitartracker.dto.error;

public record ErrorResponse(
        int code,
        String message) {
}
