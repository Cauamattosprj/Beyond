package com.beyond.beyond.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.util.UUID;

public record ActivityDTO(
    @NotBlank String name,
    @NotBlank String description,
    @NotNull Integer pointsValue,
    Boolean isDefault,
    UUID userId,
    UUID period,
    UUID activityType
) {}

