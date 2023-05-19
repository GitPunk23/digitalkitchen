package com.digitalkitchen.controller.request;

import java.util.ArrayList;
import java.util.List;

import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Recipes;

public class RecipeTransferObject {

    private int id;
    private String name;
    private String author;
    private String category;
    private int caloriesPerServing;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private int servings;
    private List<String> tags = new ArrayList<>();
    private List<String> steps = new ArrayList<>();

    public RecipeTransferObject(Recipes recipe) {
        this.id = recipe.getID();
        this.name = recipe.getName();
        this.author = recipe.getAuthor();
        this.category = recipe.getCategory().getName();
        this.caloriesPerServing = recipe.getCaloriesPerServing();
        this.description = recipe.getDescription();
        this.servings = recipe.getServings();

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            this.ingredients.add(recipe.getIngredients().get(i).toString());
        }
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            this.steps.add(recipe.getSteps().get(i).toString());
        }
        for (int i = 0; i < recipe.getTags().size(); i++) {
            this.tags.add(recipe.getTags().get(i).toString());
        }
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", caloriesPerServing=" + caloriesPerServing +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ", servings=" + servings +
                ", tags=" + tags +
                ", steps=" + steps;
    }


    
}
