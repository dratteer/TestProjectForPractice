package com.bohdan.training.TestProjectForPractice.dto;

import java.time.Instant;


public record ErrorResponseDto(int status,
                               String error,
                               String message,
                               String path,
                               Instant timestamp) {
}
