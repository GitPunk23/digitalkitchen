package com.digitalkitchen.repository;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.request.RecipeSearchRequest;

import java.util.List;

public interface RecipeRepositoryExtension {

    List<Recipe> searchRecipes(RecipeSearchRequest searchParams);
}
