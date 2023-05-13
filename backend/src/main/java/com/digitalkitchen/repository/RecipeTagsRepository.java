package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.RecipeTags;

@Repository
public interface RecipeTagsRepository extends JpaRepository<RecipeTags, Integer> {
    
}
