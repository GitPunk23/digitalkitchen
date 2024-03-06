package com.digitalkitchen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Recipes;
import com.digitalkitchen.model.entities.Steps;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Integer> {
    
    List<Steps> findAllByRecipe(Recipes recipe);

}
