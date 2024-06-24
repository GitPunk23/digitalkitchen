package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;

import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.recipes.enums.ResponseStatus.CREATED;

public class MealResponseMapper {

    public static MealResponse buildCreateResponse(List<Meal> meals, MealPlan mealPlan, List<MealRecord> mealRecords) {
        return MealResponse.builder()
                .status(CREATED)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                            .plan(mealPlan)
                            .meals(meals)
                            .records(mealRecords)
                            .build()))
                .build();
    }

}
