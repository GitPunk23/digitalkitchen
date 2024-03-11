package com.digitalkitchen.service;

import java.util.List;
import java.util.Map;

import com.digitalkitchen.model.request.RecipeRequestInfo;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.RecipeRepository;

@Service
public class RecipeService {
    
    private RecipeRepository recipeRepository;

    RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private Recipe buildRecipe(RecipeRequestInfo requestInfo) {
        return Recipe.builder()
                .name(requestInfo.getName())
                .author(requestInfo.getAuthor())
                .category(requestInfo.getCategory())
                .description(requestInfo.getDescription())
                .servings(requestInfo.getServings())
                .caloriesPerServing(requestInfo.getCaloriesPerServing())
                .notes(requestInfo.getNotes())
                .ingredients(requestInfo.getIngredients())
                .steps(requestInfo.getSteps())
                .tags(requestInfo.getTags())
                .build();
    }

    public RecipeResponse createRecipe(RecipeRequest request, boolean force) {
        Recipe recipe = buildRecipe(request.getRecipes().get(0));
        //check for duplicate if force = true (maybe better way to do this)
        //save recipe
        return ResponseMapper.buildRecipeResponse(recipe);
    }

    public List<Recipe> searchRecipes(Map<String, Object> searchParams) { 
        //return recipeRepository.searchRecipes(searchParams);
        throw new UnsupportedOperationException("Unimplemented method 'searchRecipes'");
    }

    public void updateRecipe(RecipeRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    public void deleteRecipe(int recipeID) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }
}
