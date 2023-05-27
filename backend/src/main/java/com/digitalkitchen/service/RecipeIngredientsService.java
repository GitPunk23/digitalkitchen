package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.repository.RecipeIngredientsRepository;

@Service
public class RecipeIngredientsService {

    @Autowired
    private RecipeIngredientsRepository repository;
    
    public List<RecipeIngredients> getAllRecipeIngredients() {
        return repository.findAll();
    }

    public List<RecipeIngredients> getAllRecipeIngredientsByRecipe(Recipes recipe) {
        return repository.findByRecipe(recipe);
    }
    
    public Optional<RecipeIngredients> getRecipeIngredientById(int id) {
        return repository.findById(id);
    }

    public Optional<RecipeIngredients> getRecipeIngredientByName(String name) {
        return repository.findByName(name);
    }

    public RecipeIngredients addRecipeIngredient(RecipeIngredients ingredient) {
        return repository.save(ingredient);
    }

    public List<RecipeIngredients> addRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        List<RecipeIngredients> newRecipeIngredients = new ArrayList();
        RecipeIngredients recipeIngredient;

        for (int i = 0; i < recipeIngredients.size(); i++) {
            recipeIngredient = this.addRecipeIngredient(recipeIngredients.get(i));
            newRecipeIngredients.add(recipeIngredient);
        }
        return repository.saveAll(newRecipeIngredients);
    }

    public void updateRecipeIngredient(RecipeIngredients ingredient) {
        repository.save(ingredient);
    }

    public void deleteRecipeIngredientById(int id) {
        repository.deleteById(id);
    }

}
