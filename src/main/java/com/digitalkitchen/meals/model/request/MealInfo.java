package com.digitalkitchen.meals.model.request;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min=1)
    private List<Long> recipeIds;

    @NotBlank
    private String relationId;

    public boolean isCopyOfMeal(Meal meal) {
        return meal.getName().equals(this.name) &&
                meal.getNotes().equals(this.notes) &&
                meal.getMealRecipes().stream()
                        .map(MealRecipe::getId)
                        .allMatch(recipeIds::contains);
    }
}
