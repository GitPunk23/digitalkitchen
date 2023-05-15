package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Measurements;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    
}
