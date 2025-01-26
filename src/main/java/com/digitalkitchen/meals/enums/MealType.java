package com.digitalkitchen.meals.enums;

import com.digitalkitchen.recipes.enums.Measurement;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum MealType {

    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK,
    APPETIZER,
    MEAL;

    public static List<String> getAllMealStrings() {
        return Arrays.stream(Measurement.values())
                .map(Enum::name)
                .toList();
    }

}
