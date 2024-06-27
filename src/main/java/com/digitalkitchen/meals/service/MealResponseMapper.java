package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;

import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.enums.ResponseStatus.EMPTY;
import static com.digitalkitchen.util.Constants.NOTHING_CREATED;

public class MealResponseMapper {

    private MealResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

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

    public static MealResponse buildEmptyResponse() {
        return MealResponse.builder()
                .status(EMPTY)
                .message(NOTHING_CREATED)
                .build();
    }

}
