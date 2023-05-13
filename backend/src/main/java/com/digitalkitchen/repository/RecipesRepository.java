package com.digitalkitchen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Recipes;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Integer> {
    
}
