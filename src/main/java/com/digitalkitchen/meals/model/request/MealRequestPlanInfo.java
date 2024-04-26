package com.digitalkitchen.meals.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealRequestPlanInfo {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
}
