package com.database.impl;

public class entityImplUtility {
    
    public static Entity resolveEntityType(String table, int ID) {
        switch (table) {
            case "categories":
                if (ID == -1) {
                    return new CategoryImpl();
                } else {
                    return new CategoryImpl(ID);
                }
            case "ingredients":
                if (ID == -1) {
                    return new IngredientsImpl();
                } else {
                    return new IngredientsImpl(ID);
                }
            case "measurements":
                if (ID == -1) {
                    return new MeasurementsImpl();
                } else {
                    return new MeasurementsImpl(ID);
                }
            case "recipe_ingredients":
                if (ID == -1) {
                    return new RecipeIngredientsImpl();
                } else {
                    return new RecipeIngredientsImpl(ID);
                }
            case "recipe_tags":
                if (ID == -1) {
                    return new RecipeTagsImpl();
                } else {
                    return new RecipeTagsImpl(ID);
                }
            case "recipes":
                if (ID == -1) {
                    return new RecipesImpl();
                } else {
                    return new RecipesImpl(ID);
                }
            case "steps":
                if (ID == -1) {
                    return new StepsImpl();
                } else {
                    return new StepsImpl(ID);
                }
            case "tags":
                if (ID == -1) {
                    return new TagsImpl();
                } else {
                    return new TagsImpl(ID);
                }
            default:
                throw new IllegalArgumentException("Unknown Entity Type");
        }
    }



}
