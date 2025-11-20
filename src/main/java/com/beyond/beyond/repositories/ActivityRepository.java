package com.beyond.beyond.repositories;

import org.springframework.stereotype.Repository;

import com.beyond.beyond.models.Activity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID>{
    
}
