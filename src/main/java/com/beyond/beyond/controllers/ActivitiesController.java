package com.beyond.beyond.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.beyond.dto.ActivityDTO;
import com.beyond.beyond.mappers.ActivityMapper;
import com.beyond.beyond.models.Activity;
import com.beyond.beyond.models.ActivityType;
import com.beyond.beyond.models.Period;
import com.beyond.beyond.repositories.ActivitiesRepository;
import com.beyond.beyond.repositories.ActivityTypeRepository;
import com.beyond.beyond.repositories.PeriodRepository;
import com.beyond.beyond.services.ActivitiesService;

import lombok.NonNull;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {
    private final ActivitiesRepository activitiesRepository;
    private final ActivitiesService activitiesService;
    private final ActivityMapper activityMapper;
    private final PeriodRepository periodRepository;
    private final ActivityTypeRepository activityTypeRepository;

    public ActivitiesController(
            ActivitiesRepository activitiesRepository,
            ActivitiesService activitiesService,
            ActivityMapper activityMapper,
            PeriodRepository periodRepository,
            ActivityTypeRepository activityTypeRepository) {
        this.activitiesRepository = activitiesRepository;
        this.activitiesService = activitiesService;
        this.activityMapper = activityMapper;
        this.periodRepository = periodRepository;
        this.activityTypeRepository = activityTypeRepository;
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

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> update(@PathVariable UUID id, @RequestBody ActivityDTO dto) {
        Activity activity = activitiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        ActivityType activityType = activityTypeRepository.findByType(dto.activityType()).orElseThrow(() -> new RuntimeException("Type not found"));
        Period period = periodRepository.findByName(dto.period()).orElseThrow(() -> new RuntimeException("Period not found"));
        activity.setActivityType(activityType);
        activity.setDescription(dto.description());
        activity.setIsDefault(dto.isDefault());
        activity.setName(dto.name());
        activity.setPeriod(period);
        activity.setPointsValue(dto.pointsValue());
        activity.setUserId(dto.userId());

        ActivityDTO activityDTO = activityMapper.toDto(activity);

        activitiesRepository.save(activity);

        return ResponseEntity.status(HttpStatus.OK).body(activityDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getById(@PathVariable UUID id) {
        Optional<Activity> activity = activitiesRepository.findById(id);
        
        if (activity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ActivityDTO activityDTO = activityMapper.toDto(activity.get());

        return ResponseEntity.status(HttpStatus.OK).body(activityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable UUID id) {
        activitiesRepository.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
