package com.digitalkitchen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Ingredients;

import static com.digitalkitchen.util.QueryConstants.GET_ALL_INGREDIENTS;;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
    
    Optional<Ingredients> findByIngredient(String name);

    @Query(GET_ALL_INGREDIENTS)
    List<String> getAllIngredients();


}
