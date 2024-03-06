package com.digitalkitchen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalkitchen.model.entities.Recipes;

import java.util.List;

import static com.digitalkitchen.util.QueryConstants.GET_ALL_AUTHORS;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Integer> {

    public List<Recipes> findByName(String name);

    public List<Recipes> findByNameAndAuthor(String name, String author);

    public boolean existsByNameAndAuthor(String name, String author);

    @Query(GET_ALL_AUTHORS)
    public List<String> getAllAuthors();
    
}
