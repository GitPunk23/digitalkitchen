package com.digitalkitchen.meals.model.request;

import com.digitalkitchen.meals.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealRecordInfo {
    private long id;
    private Long mealId;
    private Long mealPlanId;
    private LocalDate date;
    private MealType mealType;
}
