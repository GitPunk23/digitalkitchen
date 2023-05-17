package com.digitalkitchen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
    
    Optional<Ingredients> findByIngredient(String name);


}
