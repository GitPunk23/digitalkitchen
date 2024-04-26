package com.digitalkitchen.meals.model.response;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MealResponseInfo {

    @JsonProperty("mealPlan")
    private final MealPlan plan;

    @JsonProperty("meals")
    private final List<Meal> meals;
}
