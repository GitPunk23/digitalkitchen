package com.api.request;

import com.entities.RecipeIngredients;

public class RecipeIngredientsRequest {
    
    private int ingredientID;
    private int measurementID;
    private Float quantity;
    private String notes;

    public RecipeIngredientsRequest(int ingredientID, int measurementID, float quantity, String notes) {
        this.ingredientID = ingredientID;
        this.measurementID = measurementID;
        this.quantity = quantity;
        this.notes = notes;
    }

    /**
     * Creates a RecipeIngredients object 
     * @param recipeID the ID of the recipe relevant to this recipe ingredient
     * @return constructed RecipeIngredients object
     */
    public RecipeIngredients createRecipeIngredient(int recipeID) {
        RecipeIngredients recipeIngredient = new RecipeIngredients(recipeID, this.ingredientID,
                                             this.measurementID, this.quantity, this.notes);
        return recipeIngredient;
    }

}
