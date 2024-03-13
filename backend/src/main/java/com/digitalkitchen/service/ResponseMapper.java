package com.digitalkitchen.service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.response.RecipeResponse;

import java.util.Collections;

import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.enums.ResponseStatus.DUPLICATE;

public class ResponseMapper {
    
    private ResponseMapper() { throw new AssertionError(); }

    public static RecipeResponse buildRecipeCreationResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .status(CREATED)
                .recipes(Collections.singletonList(recipe))
                .build();
    }

    public static RecipeResponse buildRecipeDuplicateResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .status(DUPLICATE)
                .recipes(Collections.singletonList(recipe))
                .build();
    }
}
