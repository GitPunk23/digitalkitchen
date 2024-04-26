package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;
import com.digitalkitchen.recipes.enums.ResponseStatus;
import com.digitalkitchen.recipes.model.entities.Recipe;

import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.recipes.enums.ResponseStatus.CREATED;

public class MealResponseMapper {

    private MealResponseMapper() { throw new AssertionError(); }
    public static MealResponse buildCreateResponse(Meal meal) {
        return MealResponse.builder()
                .status(CREATED)
                .meals(Collections.singletonList(
                    MealResponseInfo.builder()
                    .meals(Collections.singletonList(meal))
                    .build()
                    ))
                .build();
    }
}
