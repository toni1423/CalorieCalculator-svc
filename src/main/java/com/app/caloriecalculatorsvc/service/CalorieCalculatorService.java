package com.app.caloriecalculatorsvc.service;

import com.app.caloriecalculatorsvc.dto.CalorieCalculation;
import com.app.caloriecalculatorsvc.dto.CalorieRequest;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import com.app.caloriecalculatorsvc.repository.CalorieRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CalorieCalculatorService {

    private final CalorieRecordRepository repository;

    public CalorieCalculatorService(CalorieRecordRepository repository) {
        this.repository = repository;
    }

    public CalorieRecord calculateAndSave(CalorieRequest request) {
        double bmr = 10 * request.getWeight() + 6.25 * request.getHeight() - 5 * request.getAge() + 5;
        double multiplier = switch (request.getActivityLevel()) {
            case "sedentary" -> 1.2;
            case "moderate" -> 1.55;
            case "active" -> 1.9;
            default -> 1.2;
        };

        double calories = bmr * multiplier;

        // Запазване в базата
        CalorieRecord record = new CalorieRecord();
        record.setUserId(request.getUserId());
        record.setAge(request.getAge());
        record.setWeight(request.getWeight());
        record.setHeight(request.getHeight());
        record.setActivityLevel(request.getActivityLevel());
        record.setGender(request.getGender());
        record.setCalories(calories);

        return repository.save(record);
    }

    public List<CalorieCalculation> getRecordsByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }


    public List<CalorieCalculation> getHistoryByUserId(UUID userId) {
        // Извличаме всички калорийни записи за потребителя
        List<CalorieCalculation> calorieCalculations = repository.findByUserId(userId);

        // Преобразуваме в DTO
        return calorieCalculations.stream()
                .map(calorieCalculation -> CalorieCalculation.builder()
                        .userId(calorieCalculation.getUserId())
                        .weight(calorieCalculation.getWeight())
                        .height(calorieCalculation.getHeight())
                        .age(calorieCalculation.getAge())
                        .calories(calorieCalculation.getCalories())
                        .gender(calorieCalculation.getGender())
                        .activityLevel(calorieCalculation.getActivityLevel())
                        .calculatedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }
}