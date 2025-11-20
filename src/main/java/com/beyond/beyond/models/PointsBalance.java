package com.beyond.beyond.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Table(name = "points_balance")
public class PointsBalance {
    @Id
    @JoinColumn(name = "user_id")
    private UUID userId;
    private Integer points;
}
