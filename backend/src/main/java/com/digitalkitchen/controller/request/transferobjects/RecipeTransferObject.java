package com.digitalkitchen.controller.request.transferobjects;

import java.util.ArrayList;
import java.util.List;

import com.digitalkitchen.model.entities.Ingredients;
import com.digitalkitchen.model.entities.Recipes;

public class RecipeTransferObject {

    private int id;
    private String name;
    private String author;
    private String category;
    private int caloriesPerServing;
    private String description;
    private List<RecipeIngredientsTransferObject> ingredients = new ArrayList<>();
    private int servings;
    private List<String> tags = new ArrayList<>();
    private List<StepTransferObject> steps = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(int caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecipeIngredientsTransferObject> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientsTransferObject> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<StepTransferObject> getSteps() {
        return steps;
    }

    public void setSteps(List<StepTransferObject> steps) {
        this.steps = steps;
    }

    public RecipeTransferObject(Recipes recipe) {
        this.id = recipe.getID();
        this.name = recipe.getName();
        this.author = recipe.getAuthor();
        this.category = recipe.getCategory().getName();
        this.caloriesPerServing = recipe.getCaloriesPerServing();
        this.description = recipe.getDescription();
        this.servings = recipe.getServings();

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            this.ingredients.add(new RecipeIngredientsTransferObject(recipe.getIngredients().get(i)));
        }
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            this.steps.add(new StepTransferObject(recipe.getSteps().get(i)));
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
