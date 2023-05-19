package com.digitalkitchen.service;

import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.repository.RecipesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;


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

    public Recipes getExpandedRecipe(Recipes recipe) {
        
        recipe.setIngredients(recipeIngredientsService.getAllRecipeIngredientsByRecipe(recipe));
        System.out.println(recipe.getIngredients());
        recipe.setSteps(stepsService.getAllStepsByRecipe(recipe));
        System.out.println(recipe.getSteps());
        recipe.setTags(recipeTagsService.getAllRecipeTagsByRecipe(recipe));
        System.out.println(recipe.getTags());

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

    
}
