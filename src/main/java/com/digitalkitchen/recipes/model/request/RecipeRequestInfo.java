package com.digitalkitchen.recipes.model.request;

import java.util.List;

import com.digitalkitchen.recipes.enums.Category;
import com.digitalkitchen.recipes.model.entities.RecipeIngredient;
import com.digitalkitchen.recipes.model.entities.Step;
import com.digitalkitchen.recipes.model.entities.RecipeTag;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRequestInfo {
    private Long id;
    @NotBlank
    private Category category;
    @NotBlank
    private String name;
    private String description;
    @Digits(integer = 3, fraction = 0)
    private int servings;
    @Digits(integer = 4, fraction = 0)
    private int caloriesPerServing;
    private String notes;
    @NotBlank
    private String authorName;
    @NotEmpty
    private List<RecipeIngredient> ingredients;
    @NotEmpty
    private List<Step> steps;
    private List<RecipeTag> tags;
}
