package com.digitalkitchen.recipes.repository;

import com.digitalkitchen.recipes.model.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {

}
