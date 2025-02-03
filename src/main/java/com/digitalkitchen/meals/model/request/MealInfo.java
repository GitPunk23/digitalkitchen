package com.digitalkitchen.meals.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class MealInfo {
    private String id;

    @NotNull
    private String name;
    private String notes;
    private List<Long> recipeIds;
    private List<Long> mealRecipeIds;
    @NotBlank
    private String relationId;
}
