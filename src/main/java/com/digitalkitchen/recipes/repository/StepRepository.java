package com.digitalkitchen.recipes.repository;

import com.digitalkitchen.recipes.model.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {

}
