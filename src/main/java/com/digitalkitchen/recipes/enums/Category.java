package com.digitalkitchen.recipes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Category {
    
    APPETIZER("Appetizer"),
    BEEF("Beef"),
    BREAD("Bread"),
    BREAKFAST("Breakfast"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    HOLIDAY("Holiday"),
    PASTA("Pasta"),
    PIZZA_AND_FLATBREADS("Pizza and Flatbreads"),
    PORK("Pork"),
    POULTRY("Poultry"),
    SALAD("Salad"),
    SANDWICHES_AND_WRAPS("Sandwiches and Wraps"),
    SAUCE("Sauce"),
    SAUSAGE("Sausage"),
    SEAFOOD("Seafood"),
    SIDE("Side"),
    SOUP("Soup"),
    SPICES("Spices"),
    VEGETABLES("Vegetables");

    public final String category;

    Category(String category) {
        this.category = category;
    }

    @JsonCreator
    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.category.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }

    @JsonValue
    public String getCategory() {
        return category;
    }

    public static List<String> getAllCategoryStrings() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .toList();
    }
}
