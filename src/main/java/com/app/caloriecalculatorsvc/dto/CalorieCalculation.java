package com.app.caloriecalculatorsvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CalorieCalculation {
    @NotNull
    private UUID userId;

    @NotNull
    private double weight;

    @NotNull
    private double height;

    @NotNull
    private int age;

    private double calories;

    @NotNull
    private String gender;

    @NotNull
    @NotBlank
    private String activityLevel;

    private LocalDateTime calculatedAt;


    public static class CalorieCalculationBuilder {
        public CalorieCalculationBuilder userId(UUID userId) {
            if (userId == null) {
                throw new IllegalArgumentException("UserId cannot be null");
            }
            this.userId = userId;
            return this;
        }

        public CalorieCalculationBuilder weight(double weight) {
            if (weight <= 0) {
                throw new IllegalArgumentException("Weight must be greater than 0");
            }
            this.weight = weight;
            return this;
        }

        public CalorieCalculationBuilder height(double height) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }
            this.height = height;
            return this;
        }

        public CalorieCalculationBuilder age(int age) {
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be greater than 0");
            }
            this.age = age;
            return this;
        }

        public CalorieCalculationBuilder activityLevel(String activityLevel) {
            if (activityLevel == null || activityLevel.isBlank()) {
                throw new IllegalArgumentException("Activity level cannot be blank");
            }
            this.activityLevel = activityLevel;
            return this;
        }

        public CalorieCalculationBuilder gender(String gender) {
            if (gender == null || gender.isBlank()) {
                throw new IllegalArgumentException("Gender cannot be blank");
            }
            this.gender = gender;
            return this;
        }
    }
}
