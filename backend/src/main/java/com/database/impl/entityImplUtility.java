package com.database.impl;

public class entityImplUtility {
    
    public static Entity resolveEntityType(String table, int ID) {
        switch (table) {
            case "categories":
                return new CategoryImpl(ID);
            case "ingredients":
                return new IngredientsImpl(ID);
            case "measurements":
                return new MeasurementsImpl(ID);
            case "recipe_ingredients":
                return new RecipeIngredientsImpl(ID);
            case "recipe_tags":
                return new RecipeTagsImpl(ID);
            case "recipes":
                return new RecipesImpl(ID);
            case "steps":
                return new StepsImpl(ID);
            case "tags":
                return new TagsImpl(ID);
            default:
                throw new IllegalArgumentException("Unknown Entity Type");
        }
    }



}
