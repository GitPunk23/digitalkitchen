package com.digitalkitchen.controller.request;

import java.util.List;

import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Steps;

public class CreateRecipeRequestWrapper {
    private Recipes recipe;
    private List<RecipeIngredients> ingredients;
    private List<Steps> steps;
    private List<RecipeTags> tags;

    // Getter for recipe
    public Recipes getRecipe() {
        return recipe;
    }

    // Getter for ingredients
    public List<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    // Getter for steps
    public List<Steps> getSteps() {
        return steps;
    }

    // Getter for recipeTags
    public List<RecipeTags> getRecipeTags() {
        return tags;
    }

    public String toString() {
        
        String out = "";
        out += getRecipe() + "\n";
        out += getIngredients() + "\n";
        out += getSteps() + "\n";
        out += getRecipeTags();
        
        return out;
    }
}
