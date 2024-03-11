package com.digitalkitchen.model.request;

import java.util.List;

import com.digitalkitchen.enums.Category;
import com.digitalkitchen.model.entities.RecipeIngredient;
import com.digitalkitchen.model.entities.RecipeTag;
import com.digitalkitchen.model.entities.Step;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRequestInfo {
    private Category category;
    private String name;
    private String description;
    private int servings;
    private int caloriesPerServing;
    private String notes;
    private String author;
    private List<RecipeIngredient> ingredients;
    private List<Step> steps;
    private List<RecipeTag> tags;
}
