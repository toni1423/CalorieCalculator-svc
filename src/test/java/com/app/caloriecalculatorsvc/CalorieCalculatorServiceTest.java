package com.app.caloriecalculatorsvc;

import com.app.caloriecalculatorsvc.dto.CalorieCalculation;
import com.app.caloriecalculatorsvc.dto.CalorieRequest;
import com.app.caloriecalculatorsvc.model.CalorieRecord;
import com.app.caloriecalculatorsvc.repository.CalorieRecordRepository;
import com.app.caloriecalculatorsvc.service.CalorieCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalorieCalculatorServiceTest {
    @Mock
    private CalorieRecordRepository repository;

    @InjectMocks
    private CalorieCalculatorService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAndSave_ShouldReturnSavedRecord() {
        // Arrange
        UUID userId = UUID.randomUUID();
        CalorieRequest request = new CalorieRequest();
        request.setUserId(userId);
        request.setWeight(70.0);
        request.setHeight(175.0);
        request.setAge(30);
        request.setActivityLevel("moderate");
        request.setGender("male");

        double expectedCalories = (10 * 70.0) + (6.25 * 175.0) - (5 * 30) + 5;
        expectedCalories = expectedCalories * 1.55; // moderate multiplier

        CalorieRecord savedRecord = new CalorieRecord();
        savedRecord.setUserId(userId);
        savedRecord.setAge(30);
        savedRecord.setWeight(70.0);
        savedRecord.setHeight(175.0);
        savedRecord.setActivityLevel("moderate");
        savedRecord.setGender("male");
        savedRecord.setCalories(expectedCalories);

        when(repository.save(any(CalorieRecord.class))).thenReturn(savedRecord);

        
        CalorieRecord result = service.calculateAndSave(request);

        
        assertNotNull(result);
        assertEquals(expectedCalories, result.getCalories());
        verify(repository, times(1)).save(any(CalorieRecord.class)); // Verify if save is called
    }

    @Test
    void testGetRecordsByUserId_ShouldReturnRecords() {
        
        UUID userId = UUID.randomUUID();

        
        List<CalorieCalculation> records = List.of(
                CalorieCalculation.builder()
                        .userId(userId)
                        .weight(70.0)
                        .height(175.0)
                        .age(30)
                        .activityLevel("moderate")
                        .gender("male")
                        .calories(2500.0)
                        .build(),
                CalorieCalculation.builder()
                        .userId(userId)
                        .weight(72.0)
                        .height(180.0)
                        .age(28)
                        .activityLevel("moderate")
                        .gender("female")
                        .calories(2300.0)
                        .build()
        );

        
        when(repository.findByUserId(userId)).thenReturn(records);

        
        List<CalorieCalculation> result = service.getRecordsByUserId(userId);

        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findByUserId(userId); // Verify repository method was called
    }
}
