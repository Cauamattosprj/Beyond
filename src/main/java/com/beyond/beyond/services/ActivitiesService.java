package com.beyond.beyond.services;

import org.springframework.stereotype.Service;

import com.beyond.beyond.models.Activity;
import com.beyond.beyond.repositories.ActivitiesRepository;

@Service
public class ActivitiesService {
    private ActivitiesRepository activitiesRepository;

    public ActivitiesService(
        ActivitiesRepository activitiesRepository
    ) {
        this.activitiesRepository = activitiesRepository;
    }

    public Activity save(Activity activity) {
        return activitiesRepository.save(activity);
    }
}
