package com.digitalkitchen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Measurements;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

    Optional<Measurements> findByMeasurement(String updatedName);
    
}
