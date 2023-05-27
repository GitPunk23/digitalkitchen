package com.digitalkitchen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.Recipes;

@Repository
public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Integer> {

    public List<RecipeIngredients> findByRecipe(Recipes recipe);

    public Optional<RecipeIngredients> findByName(String name);
        
    
    
}