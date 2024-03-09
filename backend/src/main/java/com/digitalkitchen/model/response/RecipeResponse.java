package com.digitalkitchen.model.response;

import java.util.List;

import com.digitalkitchen.model.entities.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RecipeResponse {
    
    @JsonProperty("recipes")
    private final List<Recipe> recipes;
}
