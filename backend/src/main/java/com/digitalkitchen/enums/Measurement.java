package com.digitalkitchen.enums;

import lombok.Getter;

@Getter
public enum Measurement {
    
    CUP("cup"),
    FLUID_OUNCE("fluid ounce"),
    GALLON("gallon"),
    GRAM("gram"),
    MILLIGRAM("milligram"),
    OUNCE("ounce"),
    PINT("pint"),
    POUND("pound"),
    QUART("quart"),
    TABLESPOON("tablespoon"),
    TEASPOON("teaspoon"),
    WHOLE("whole");

    private final String displayName;

    Measurement(final String displayName) { this.displayName = displayName; }
}