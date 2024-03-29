package com.digitalkitchen.recipes.model.response;

import java.util.List;

import com.digitalkitchen.recipes.enums.ResponseStatus;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RecipeResponse {

    @JsonProperty("status")
    public ResponseStatus status;

    @JsonProperty("recipes")
    private final List<Recipe> recipes;
}
