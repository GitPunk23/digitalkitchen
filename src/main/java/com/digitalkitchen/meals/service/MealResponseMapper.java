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
import static com.digitalkitchen.enums.ResponseStatus.FOUND;
import static com.digitalkitchen.enums.ResponseStatus.UPDATED;
import static java.util.Objects.nonNull;

public class MealResponseMapper {

    private MealResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MealResponse buildCreateResponse(List<Meal> meals, MealPlan mealPlan, List<MealRecord> mealRecords) {
        return MealResponse.builder()
                .status(CREATED)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                            .plans(nonNull(mealPlan)
                                    ? List.of(mealPlan)
                                    : null)
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

    public static MealResponse buildSearchResponse(List<Meal> meals) {
        return MealResponse.builder()
                .status(FOUND)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                                .meals(meals)
                                .build()))
                .build();
    }

    public static MealResponse buildSearchResponse(MealPlan plan) {
        List<MealPlan> plans = List.of(plan);
        List<MealRecord> mealRecords = plans.get(0).getMealRecords();
        List<Meal> meals = mealRecords.stream()
                .map(MealRecord::getMeal)
                .toList();
        return MealResponseMapper.buildSearchResponse(meals, mealRecords, plans);
    }

    public static MealResponse buildSearchResponse(MealRecord mealRecord) {
        List<MealRecord> mealRecords = List.of(mealRecord);
        List<Meal> meals = List.of(mealRecord.getMeal());
        List<MealPlan> plans = List.of(mealRecord.getMealPlan());
        return MealResponseMapper.buildSearchResponse(meals, mealRecords, plans);
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

    public static MealResponse buildUpdateResponse(List<Meal> meals, MealPlan mealPlan, List<MealRecord> mealRecords) {
        MealResponse response = buildCreateResponse(meals, mealPlan, mealRecords);
        response.setStatus(UPDATED);
        return response;
    }
}
