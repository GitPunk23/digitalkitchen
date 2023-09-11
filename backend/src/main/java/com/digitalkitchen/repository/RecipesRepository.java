package com.digitalkitchen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.digitalkitchen.entities.Recipes;
import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Integer> {

    public List<Recipes> findByName(String name);

    public List<Recipes> findByNameAndAuthor(String name, String author);
    
}
