package com.app.caloriecalculatorsvc.service;

import com.app.caloriecalculatorsvc.dto.CalorieRequest;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import com.app.caloriecalculatorsvc.repository.CalorieRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CalorieRecord> getAllRecords() {
        return repository.findAll();
    }
}