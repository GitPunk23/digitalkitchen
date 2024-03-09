package com.digitalkitchen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Recipe;

import java.util.List;
import java.util.Map;

import static com.digitalkitchen.util.QueryConstants.GET_ALL_AUTHORS;
import static com.digitalkitchen.util.QueryConstants.SEARCH_RECIPES;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    public List<Recipe> findByName(String name);

    public List<Recipe> findByNameAndAuthor(String name, String author);

    public boolean existsByNameAndAuthor(String name, String author);

    @Query(GET_ALL_AUTHORS)
    public List<String> getAllAuthors();

    @Query(SEARCH_RECIPES)
    public List<Recipe> searchRecipes(Map<String, Object> searchParams);
    
}
