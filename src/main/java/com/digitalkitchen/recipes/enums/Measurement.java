package com.digitalkitchen.recipes.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Measurement {
    
    CUP,
    FLUID_OUNCE,
    GALLON,
    GRAM,
    MILLIGRAM,
    OUNCE,
    PINT,
    POUND,
    QUART,
    TABLESPOON,
    TEASPOON,
    WHOLE;

    public static List<String> getAllMeasurementStrings() {
        return Arrays.stream(Measurement.values())
                .map(Enum::name)
                .toList();
    }
}