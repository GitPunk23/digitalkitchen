package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.enums.ResponseStatus.EMPTY;
import static com.digitalkitchen.enums.ResponseStatus.FOUND;
import static com.digitalkitchen.util.Constants.NOTHING_CREATED;

public class MealResponseMapper {

    private MealResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MealResponse buildCreateResponse(List<Meal> meals, List<MealPlan> mealPlan, List<MealRecord> mealRecords) {
        return MealResponse.builder()
                .status(CREATED)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                            .plans(mealPlan)
                            .meals(meals)
                            .records(mealRecords)
                            .build()))
                .build();
    }

    public static MealResponse buildEmptyResponse(String message) {
        return MealResponse.builder()
                .status(EMPTY)
                .message(message)
                .build();
    }

    public static MealResponse buildSearchResponse(Meal meal) {
        return MealResponse.builder()
                .status(FOUND)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                                .meals(Collections.singletonList(meal))
                                .build()))
                .build();
    }

    public static MealResponse buildSearchResponse(List<Meal> meals, List<MealRecord> records, List<MealPlan> plans) {
        return MealResponse.builder()
                .status(FOUND)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                                .plans(plans)
                                .meals(meals)
                                .records(records)
                                .build()))
                .build();
    }
}
