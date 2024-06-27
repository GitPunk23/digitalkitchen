package com.digitalkitchen.meals.repository;

import com.digitalkitchen.meals.model.entities.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
}
