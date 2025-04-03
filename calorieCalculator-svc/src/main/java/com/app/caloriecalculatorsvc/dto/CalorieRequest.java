package com.app.caloriecalculatorsvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalorieRequest {


    @NotNull
    private UUID userId;

    @NotNull
    private double weight;

    @NotNull
    private double height;

    @NotNull
    private int age;

    @NotNull
    private String gender;

    @NotNull
    @NotBlank
    private String activityLevel;

}
