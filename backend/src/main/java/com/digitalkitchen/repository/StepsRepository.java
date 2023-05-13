package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Steps;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Integer> {
    
}
