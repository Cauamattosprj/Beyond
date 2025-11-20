package com.beyond.beyond.repositories;

import com.beyond.beyond.models.PointsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PointsTransactionRepository extends JpaRepository<PointsTransaction, UUID> {
}
