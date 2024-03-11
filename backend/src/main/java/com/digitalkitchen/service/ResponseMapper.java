package com.digitalkitchen.service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.response.RecipeResponse;

import java.util.Collections;

public class ResponseMapper {
    
    private ResponseMapper() { throw new AssertionError(); }

    public static RecipeResponse buildRecipeResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .recipes(Collections.singletonList(recipe))
                .build();
    }
}
