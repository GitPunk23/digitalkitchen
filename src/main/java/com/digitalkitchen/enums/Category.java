package com.digitalkitchen.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Category {
    
    APPETIZER,
    BEEF,
    BREAD,
    BREAKFAST,
    DESSERT,
    DRINK,
    HOLIDAY,
    PASTA,
    PIZZA_AND_FLATBREADS,
    PORK,
    POULTRY,
    SALAD,
    SANDWICHES_AND_WRAPS,
    SAUCE,
    SAUSAGE,
    SEAFOOD,
    SIDE,
    SOUP,
    SPICES,
    VEGETABLES;

    public static List<String> getAllCategoryStrings() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
