package com.app.caloriecalculatorsvc.controller;
import com.app.caloriecalculatorsvc.dto.CalorieRequest;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import com.app.caloriecalculatorsvc.service.CalorieCalculatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calories")
public class CalorieController {
    private final CalorieCalculatorService service;

    public CalorieController(CalorieCalculatorService service) {
        this.service = service;
    }

    @PostMapping("/calculate")
    public CalorieRecord calculateAndSave(@RequestBody CalorieRequest request) {
        return service.calculateAndSave(request);
    }

    @GetMapping("/records")
    public List<CalorieRecord> getAllRecords() {
        return service.getAllRecords();
    }

//


}
