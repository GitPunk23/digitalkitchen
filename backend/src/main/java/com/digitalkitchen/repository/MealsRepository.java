package com.digitalkitchen.repository;

import com.digitalkitchen.entities.Meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealsRepository extends JpaRepository<Meal, Integer>  {
    
}
