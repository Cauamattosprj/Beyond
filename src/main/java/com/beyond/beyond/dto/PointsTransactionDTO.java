package com.beyond.beyond.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record PointsTransactionDTO(
    UUID id,
    @NotBlank UUID activityId,
    @NotBlank UUID userId,
    String period,
    @NotBlank Integer periodAmount,
    Integer pointsDelta
) {}