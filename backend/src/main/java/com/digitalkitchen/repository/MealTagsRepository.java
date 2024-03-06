package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.MealTags;

@Repository
public interface MealTagsRepository extends JpaRepository<MealTags, Integer> {
    
}
