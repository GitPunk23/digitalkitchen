package com.api.request;

import com.entities.RecipeTags;

public class RecipeTagsRequest {
    
    private int tagID;

    public RecipeTagsRequest(int tagID) {
        this.tagID = tagID;
    }

    public RecipeTags createRecipeTag(int recipeID) {
        RecipeTags recipeTag = new RecipeTags(recipeID, this.tagID);
        return recipeTag; 
    }
}
