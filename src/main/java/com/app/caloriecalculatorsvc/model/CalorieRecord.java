package com.app.caloriecalculatorsvc.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "calorie_records")
@NoArgsConstructor
@AllArgsConstructor
public class CalorieRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;


    private double weight;


    private double height;

    private int age;


    private String gender;

    private String activityLevel;

    private double calories;

    private LocalDateTime calculatedAt;

}
