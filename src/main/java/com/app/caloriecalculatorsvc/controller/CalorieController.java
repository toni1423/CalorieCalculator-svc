package com.app.caloriecalculatorsvc.controller;

import com.app.caloriecalculatorsvc.dto.CalorieCalculation;
import com.app.caloriecalculatorsvc.dto.CalorieRequest;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import com.app.caloriecalculatorsvc.service.CalorieCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/calories")
public class CalorieController {
    private final CalorieCalculatorService service;

    public CalorieController(CalorieCalculatorService service) {
        this.service = service;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalorieRecord> calculateAndSave(@RequestBody CalorieRequest request) {
        CalorieRecord savedRecord = service.calculateAndSave(request);
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping("/records/user/{userId}")
    public ResponseEntity<List<CalorieCalculation>> getRecordsByUser(@PathVariable UUID userId) {
        List<CalorieCalculation> records = service.getRecordsByUserId(userId);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }

    @GetMapping("/calories/user/{userId}/history")
    public List<CalorieCalculation> getHistoryByUserId(@PathVariable UUID userId) {
        return service.getHistoryByUserId(userId);
    }
}
