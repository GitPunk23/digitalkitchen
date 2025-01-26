package com.digitalkitchen.meals.repository;

import com.digitalkitchen.meals.model.entities.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

}
