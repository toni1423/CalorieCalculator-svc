package com.app.caloriecalculatorsvc.repository;

import com.app.caloriecalculatorsvc.dto.CalorieCalculation;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalorieRecordRepository extends JpaRepository<CalorieRecord, UUID> {
    List<CalorieCalculation> findByUserId(UUID userId);
}
