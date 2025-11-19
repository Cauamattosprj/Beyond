package com.beyond.beyond.models;

import java.util.UUID;

import com.beyond.beyond.dto.ActivityDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "points_value")
    private Integer pointsValue;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ActivityType activityType;

    public ActivityDTO toDto() {
        ActivityDTO dto = new ActivityDTO(getId(), getName(), getDescription(), getPointsValue(), getIsDefault(), getUserId(), getPeriod().getName(), getActivityType().getType());

        return dto;
    }

}
