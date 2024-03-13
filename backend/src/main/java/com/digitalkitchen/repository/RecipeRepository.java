package com.digitalkitchen.repository;
import com.digitalkitchen.model.request.RecipeSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.digitalkitchen.util.QueryConstants.GET_ALL_AUTHORS;
import static com.digitalkitchen.util.QueryConstants.SEARCH_RECIPES;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    public List<Recipe> findByName(String name);

    public Optional<Recipe> findByNameAndAuthor(String name, String author);

    public boolean existsByNameAndAuthor(String name, String author);

    @Query(GET_ALL_AUTHORS)
    public List<String> getAllAuthors();

    @Query(SEARCH_RECIPES)
    List<Recipe> searchRecipes(
            @Param("name") String name,
            @Param("categories") List<String> categories,
            @Param("authors") List<String> authors,
            @Param("tags") List<String> tags,
            @Param("ingredients") List<String> ingredients,
            @Param("servings") List<Integer> servings,
            @Param("calories") List<Integer> calories
    );
    
}
