package com.beyond.beyond.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "activities")
@Data
public class Activity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Column(name = "points_value")
    private Integer pointsValue;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "period_id")
    private UUID periodId;

    @Column(name = "activity_type_id")
    private UUID activityTypeId;
}
