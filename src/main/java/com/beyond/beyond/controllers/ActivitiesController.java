package com.beyond.beyond.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.beyond.dto.ActivityDTO;
import com.beyond.beyond.mappers.ActivityMapper;
import com.beyond.beyond.models.Activity;
import com.beyond.beyond.repositories.ActivitiesRepository;
import com.beyond.beyond.services.ActivitiesService;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {
    private final ActivitiesRepository activitiesRepository;
    private final ActivitiesService activitiesService;
    private final ActivityMapper activityMapper;

    public ActivitiesController(
            ActivitiesRepository activitiesRepository,
            ActivitiesService activitiesService,
            ActivityMapper activityMapper) {
        this.activitiesRepository = activitiesRepository;
        this.activitiesService = activitiesService;
        this.activityMapper = activityMapper;
    }

    @PostMapping("")
    public ResponseEntity<ActivityDTO> post(@RequestBody ActivityDTO dto) {

        Activity activity = activityMapper.toEntity(dto);
        Activity savedActivity = activitiesRepository.save(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity.toDto());
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityDTO>> getAll() {
        List<Activity> allActivities = activitiesRepository.findAll();
        List<ActivityDTO> allActivityDTOs = new ArrayList<ActivityDTO>();
        for (Activity activity : allActivities) {
            ActivityDTO dto = activityMapper.toDto(activity);
            allActivityDTOs.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(allActivityDTOs);
    }

    @GetMapping("/{id}")
    public Optional<Activity> getById(@PathVariable UUID id) {
        Optional<Activity> activity = activitiesRepository.findById(id);

        return activity;
    }

}
