package com.digitalkitchen.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }
}