package com.api.request;

import com.entities.Recipes;

public class RecipesRequest {
    
    private int categoryID;
    private String name;
    private String description;
    private int servings;
    private int caloriesPerServing;
    private String notes;
    
    public RecipesRequest(int categoryID, String name, String description, int servings, int caloriesPerServing,
                    String notes) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.caloriesPerServing = caloriesPerServing;
        this.notes = notes;
    }

    public Recipes createRecipe() {
        Recipes recipe = new Recipes(this.categoryID, this.name, this.description, this.servings,
                                     this.caloriesPerServing, this.notes);
        return recipe;
    }
}
