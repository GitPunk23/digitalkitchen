package com.digitalkitchen.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.entities.Recipes;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Integer> {

    public Optional<Recipes> findByName(String name);
    
}
