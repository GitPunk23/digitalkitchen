package com.digitalkitchen.recipes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Measurement {
    
    CUP("Cup"),
    FLUID_OUNCE("Fluid Ounce"),
    GALLON("Gallon"),
    GRAM("Gram"),
    MILLIGRAM("Milligram"),
    OUNCE("Ounce"),
    PINT("Pint"),
    POUND("Pound"),
    QUART("Quart"),
    TABLESPOON("Tablespoon"),
    TEASPOON("Teaspoon"),
    WHOLE("Whole");

    public final String measurement;

    Measurement(String measurement) {
        this.measurement = measurement;
    }

    @JsonCreator
    public static Measurement fromValue(String value) {
        for (Measurement measurement : Measurement.values()) {
            if (measurement.measurement.equalsIgnoreCase(value)) {
                return measurement;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }

    @JsonValue
    public String getMeasurement() {
        return measurement;
    }

    public static List<String> getAllMeasurementStrings() {
        return Arrays.stream(Measurement.values())
                .map(Enum::name)
                .toList();
    }
}