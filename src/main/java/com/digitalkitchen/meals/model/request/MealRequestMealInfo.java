package com.digitalkitchen.meals.model.request;

import com.digitalkitchen.meals.enums.MealType;
import lombok.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealRequestMealInfo {
    private long id;
    private String name;
    private LocalDate date;
    private MealType type;
    private String mealPlanId;
    private String notes;
    private List<Integer> recipeIds;
}
