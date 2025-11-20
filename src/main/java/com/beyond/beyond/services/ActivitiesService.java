package com.beyond.beyond.services;

import org.springframework.stereotype.Service;

import com.beyond.beyond.models.Activity;
import com.beyond.beyond.repositories.ActivityRepository;

@Service
public class ActivitiesService {
    private ActivityRepository activityRepository;

    public ActivitiesService(
        ActivityRepository activityRepository
    ) {
        this.activityRepository = activityRepository;
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }
}
