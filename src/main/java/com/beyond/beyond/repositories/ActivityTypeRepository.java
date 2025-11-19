package com.beyond.beyond.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyond.beyond.models.ActivityType;


public interface ActivityTypeRepository extends JpaRepository<ActivityType, UUID> {
    public Optional<ActivityType> findByType(String type);
}
