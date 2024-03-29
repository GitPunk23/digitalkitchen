package com.digitalkitchen.recipes.repository;

import com.digitalkitchen.recipes.model.entities.RecipeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeTagRepository extends JpaRepository<RecipeTag, Integer> {

}
