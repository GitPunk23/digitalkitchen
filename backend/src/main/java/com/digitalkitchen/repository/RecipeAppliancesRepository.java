package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.RecipeAppliances;

@Repository
public interface RecipeAppliancesRepository extends JpaRepository<RecipeAppliances, Integer> {
    
}
