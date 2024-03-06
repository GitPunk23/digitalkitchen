package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Meal;

@Repository
public interface MealsRepository extends JpaRepository<Meal, Integer>  {
    
}
