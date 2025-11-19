package com.beyond.beyond.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyond.beyond.models.Period;
import java.util.List;
import java.util.Optional;


public interface PeriodRepository extends JpaRepository<Period, UUID>{
    Optional<Period> findByName(String name);
}
