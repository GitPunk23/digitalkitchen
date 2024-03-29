package com.digitalkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Appliances;

@Repository
public interface AppliancesRepository extends JpaRepository<Appliances, Integer> {
    
}
