package com.digitalkitchen.enums;

import lombok.Getter;

@Getter
public enum Category {
    
    APPETIZER("appetizer"),
    BEEF("beef"),
    BREAD("bread"),
    BREAKFAST("breakfast"),
    DESSERT("dessert"),
    DRINK("drink"),
    HOLIDAY("holiday"),
    PASTA("pasta"),
    PIZZA_AND_FLATBREADS("pizza & flatbreads"),
    PORK("pork"),
    POULTRY("poultry"),
    SALAD("salad"),
    SANDWICHES_AND_WRAPS("sandwiches & wraps"),
    SAUCE("sauce"),
    SAUSAGE("sausage"),
    SEAFOOD("seafood"),
    SIDE("side"),
    SOUP("soup"),
    SPICES("spices"),
    VEGETABLES("vegetables");

    private final String displayName;

    Category(final String displayName) { this.displayName = displayName; }
}
