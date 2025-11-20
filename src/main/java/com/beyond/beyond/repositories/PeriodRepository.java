package com.beyond.beyond.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyond.beyond.models.Period;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, UUID>{
    Optional<Period> findByName(String name);
}
