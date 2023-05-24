package com.digitalkitchen.service;

import com.digitalkitchen.controller.request.transferobjects.RecipeTransferObject;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.repository.RecipesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class RecipesService {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private RecipeIngredientsService recipeIngredientsService;
    @Autowired
    private RecipeTagsService recipeTagsService;
    @Autowired
    private StepsService stepsService;

    public RecipesService() {
    }

    public List<Recipes> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public Optional<Recipes> getRecipeById(int id) {
        return recipesRepository.findById(id);
    }

    public Optional<Recipes> getRecipeByName(String name) {
        return recipesRepository.findByName(name);
    }

    public Recipes getExpandedRecipe(Recipes recipe) {
        recipe.setIngredients(recipeIngredientsService.getAllRecipeIngredientsByRecipe(recipe));
        recipe.setSteps(stepsService.getAllStepsByRecipe(recipe));
        recipe.setTags(recipeTagsService.getAllRecipeTagsByRecipe(recipe));
        return recipe;
    }

    public Recipes addRecipe(Recipes recipe) {
        return recipesRepository.save(recipe);
    }

    public void updateRecipe(Recipes recipe) {
        recipesRepository.save(recipe);
    }

    public void deleteRecipeById(int id) {
        recipesRepository.deleteById(id);
    }

    public boolean recipeExists(Recipes recipe) {
        Optional<Recipes> dbrecipe = recipesRepository.findByName(recipe.getName());
        if (dbrecipe.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    public RecipeTransferObject createTransferObject(Recipes recipe) {
        return new RecipeTransferObject(recipe);
    }

    
}
