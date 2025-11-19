package com.beyond.beyond.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.util.UUID;

import com.beyond.beyond.models.Activity;
import com.beyond.beyond.models.ActivityType;
import com.beyond.beyond.models.Period;

public record ActivityDTO(
    UUID id,
    @NotBlank String name,
    @NotBlank String description,
    @NotNull Integer pointsValue,
    @NotNull Boolean isDefault,
    UUID userId,
    @NotNull String period,
    @NotNull String activityType
) {
}

