package com.digitalkitchen.recipes.model.request;

import java.util.List;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.recipes.enums.Category;
import com.digitalkitchen.recipes.model.entities.RecipeIngredient;
import com.digitalkitchen.recipes.model.entities.Step;
import com.digitalkitchen.recipes.model.entities.RecipeTag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRequestInfo {
    private int id;
    private Category category;
    private String name;
    private String description;
    private int servings;
    private int caloriesPerServing;
    private String notes;
    private Author author;
    private List<RecipeIngredient> ingredients;
    private List<Step> steps;
    private List<RecipeTag> tags;
}
