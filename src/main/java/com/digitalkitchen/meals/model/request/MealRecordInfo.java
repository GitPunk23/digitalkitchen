package com.digitalkitchen.meals.model.request;

import com.digitalkitchen.meals.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class MealRecordInfo {

    private String id;

    private String mealId;

    private String mealPlanId;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    private MealType mealType;

    @NotNull
    private String relationId;
}
