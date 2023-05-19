package com.digitalkitchen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;

@Repository
public interface RecipeTagsRepository extends JpaRepository<RecipeTags, Integer> {
    
    List<RecipeTags> findAllByRecipe(Recipes recipe);

}
