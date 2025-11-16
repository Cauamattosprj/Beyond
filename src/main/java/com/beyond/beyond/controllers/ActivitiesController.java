package com.beyond.beyond.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.beyond.dto.ActivityDTO;


@RestController
public class ActivitiesController {
    @PostMapping("/activities")
    public ActivityDTO postActivities(@RequestBody ActivityDTO activitie) {
        
        return activitie;
    }
    
}
