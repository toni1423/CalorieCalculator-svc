package com.app.caloriecalculatorsvc.controller;
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

    // POST: Изчисляване и запазване
    @PostMapping("/calculate")
    public ResponseEntity<CalorieRecord> calculateAndSave(@RequestBody CalorieRequest request) {
        CalorieRecord savedRecord = service.calculateAndSave(request);
        return ResponseEntity.ok(savedRecord); // status 200 OK
    }

    // GET: Извличане на всички записи
    @GetMapping("/records")
    public ResponseEntity<List<CalorieRecord>> getAllRecords() {
        List<CalorieRecord> records = service.getAllRecords();
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build(); // status 204 No Content
        }
        return ResponseEntity.ok(records); // status 200 OK
    }

    // GET: Единичен запис по ID (опционално)
    @GetMapping("/records/{id}")
    public ResponseEntity<CalorieRecord> getRecordById(@PathVariable UUID id) {
        return service.getRecordById(id)
                .map(ResponseEntity::ok)              // status 200 OK
                .orElse(ResponseEntity.notFound().build()); // status 404 Not Found
    }

}
