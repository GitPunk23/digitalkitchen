package com.digitalkitchen.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.repository.RecipeRepository;

@Service
public class RecipeService {
    
    private RecipeRepository recipeRepository;

    RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void createRecipe(RecipeRequest request, boolean force) {
        //get recipe from request
        //check for duplicate if force = true (maybe better way to do this)
        //save recipe
        throw new UnsupportedOperationException("Unimplemented method 'createRecipe'");
    }

    public List<Recipe> searchRecipes(Map<String, Object> searchParams) { 
        //return recipeRepository.searchRecipes(searchParams);
        throw new UnsupportedOperationException("Unimplemented method 'searchRecipes'");
    }

    public void updateRecipe(RecipeRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    public void deleteRecipe(int recipeID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }
}
