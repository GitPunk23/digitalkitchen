package com.digitalkitchen.recipes.repository;

import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.model.request.RecipeSearchRequest;

import java.util.List;

public interface RecipeRepositoryExtension {

    List<Recipe> searchRecipes(RecipeSearchRequest searchParams);
}
