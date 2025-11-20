package com.beyond.beyond.controllers;

import com.beyond.beyond.mappers.PointsTransactionMapper;
import com.beyond.beyond.models.PointsTransaction;
import com.beyond.beyond.repositories.PointsTransactionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.beyond.dto.PointsTransactionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pointsTransactions")
public class PointsTransactionController {
    @Autowired
    PointsTransactionMapper pointsTransactionsMapper;
    @Autowired
    PointsTransactionRepository pointsTransactionRepository;

    @PostMapping("")
    public ResponseEntity<PointsTransactionDTO> create(@RequestBody PointsTransactionDTO dto) {
        PointsTransaction pointsTransaction = pointsTransactionsMapper.toEntity(dto);
        PointsTransaction savedPointsTransaction = pointsTransactionRepository.save(pointsTransaction);


        return ResponseEntity.status(HttpStatus.CREATED).body(pointsTransactionsMapper.toDto(savedPointsTransaction));
    }
    
}
