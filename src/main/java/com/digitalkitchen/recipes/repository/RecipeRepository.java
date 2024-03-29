package com.digitalkitchen.recipes.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.recipes.model.entities.Recipe;

import java.util.List;
import java.util.Optional;

import static com.digitalkitchen.recipes.util.QueryConstants.GET_ALL_AUTHORS;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    public Optional<Recipe> findByNameAndAuthor(String name, String author);

    @Query(GET_ALL_AUTHORS)
    public List<String> getAllAuthors();


    
}
