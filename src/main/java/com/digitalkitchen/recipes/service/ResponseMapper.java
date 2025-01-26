package com.digitalkitchen.recipes.service;

import com.digitalkitchen.enums.ResponseStatus;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.model.response.RecipeResponse;

import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.enums.ResponseStatus.*;

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

    public static RecipeResponse buildRecipeSearchResponse(List<Recipe> recipes) {
        ResponseStatus status = (recipes.isEmpty()) ? EMPTY : FOUND;
        return RecipeResponse.builder()
                .status(status)
                .recipes(recipes)
                .build();
    }
}
