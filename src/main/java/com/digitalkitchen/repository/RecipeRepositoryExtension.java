package com.digitalkitchen.repository;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.request.RecipeSearchRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.digitalkitchen.util.QueryConstants.SEARCH_RECIPES;

public interface RecipeRepositoryExtension {

    List<Recipe> searchRecipes(RecipeSearchRequest searchParams);
}
