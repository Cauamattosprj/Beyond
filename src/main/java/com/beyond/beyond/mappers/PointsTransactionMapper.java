package com.beyond.beyond.mappers;

import com.beyond.beyond.dto.PointsTransactionDTO;
import com.beyond.beyond.models.Activity;
import com.beyond.beyond.models.Period;
import com.beyond.beyond.models.PointsTransaction;
import com.beyond.beyond.models.User;
import com.beyond.beyond.repositories.ActivityRepository;
import com.beyond.beyond.repositories.PeriodRepository;
import com.beyond.beyond.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointsTransactionMapper {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    UserRepository userRepository;

    public PointsTransaction toEntity(PointsTransactionDTO dto) {
        PointsTransaction entity = new PointsTransaction();
        Activity activity = activityRepository.findById(dto.activityId()).orElseThrow(() -> new RuntimeException("Id not found"));
        Period period = periodRepository.findByName(dto.period().toUpperCase()).orElseThrow(() -> new RuntimeException("Period not found"));
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new RuntimeException("Period not found"));

        entity.setActivity(activity);
        entity.setId(dto.id());
        entity.setPeriod(period);
        entity.setPeriodAmount(dto.periodAmount());
        entity.setPointsDelta(dto.pointsDelta());
        entity.setUser(user);

        return entity;
    }

    public PointsTransactionDTO toDto(PointsTransaction entity) {
        PointsTransactionDTO dto = new PointsTransactionDTO(
                entity.getId(),
                entity.getActivity().getId(),
                entity.getUser().getId(),
                entity.getPeriod().getName(),
                entity.getPeriodAmount(),
                entity.getPointsDelta()
        );

        return dto;
    }
}
