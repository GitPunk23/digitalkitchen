package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.MealPrepDate;

@Repository
public interface MealPrepDateRepository extends JpaRepository<MealPrepDate, Integer> {
    
}
