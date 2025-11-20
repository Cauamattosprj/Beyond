package com.beyond.beyond.mappers;

import org.springframework.stereotype.Component;

import com.beyond.beyond.dto.ActivityDTO;
import com.beyond.beyond.models.Activity;
import com.beyond.beyond.models.ActivityType;
import com.beyond.beyond.models.Period;
import com.beyond.beyond.repositories.ActivityTypeRepository;
import com.beyond.beyond.repositories.PeriodRepository;

@Component
public class ActivityMapper {
    private ActivityTypeRepository activityTypeRepository;
    private PeriodRepository periodRepository;

    public ActivityMapper(
            ActivityTypeRepository activityTypeRepository,
            PeriodRepository periodRepository) {
        this.activityTypeRepository = activityTypeRepository;
        this.periodRepository = periodRepository;
    }

    public Activity toEntity(ActivityDTO dto) {
        ActivityType activityType = activityTypeRepository.findByType(dto.activityType())
                .orElseThrow(() -> new RuntimeException("Activity Type not found"));
        Period period = periodRepository.findByName(dto.period())
                .orElseThrow(() -> new RuntimeException("Period not found"));

        Activity activity = new Activity();
        if (dto.id() != null) {
            activity.setId(dto.id());
        }
        activity.setName(dto.name());
        activity.setDescription(dto.description());
        activity.setActivityType(activityType);
        activity.setIsDefault(dto.isDefault());
        activity.setPeriod(period);
        activity.setPointsValue(dto.pointsValue());

        return activity;
    }

    public ActivityDTO toDto(Activity entity) {
        ActivityDTO activityDTO = new ActivityDTO(entity.getId(), entity.getName(), entity.getDescription(),
                entity.getPointsValue(), entity.getIsDefault(), entity.getUserId(), entity.getPeriod().toString(),
                entity.getActivityType().toString());

        return activityDTO;
    }
}
