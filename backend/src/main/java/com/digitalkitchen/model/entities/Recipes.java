package com.digitalkitchen.model.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.digitalkitchen.util.CategoryDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "recipes")
@Table(name = "recipes",
       uniqueConstraints = {
            @UniqueConstraint(name = "recipe_name_unique", columnNames = "name")
       })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
                name = "category_id", 
                referencedColumnName = "id",
                nullable = false)
    @JsonDeserialize(using = CategoryDeserializer.class)
    private Category category;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int servings;

    @Column
    private int caloriesPerServing;

    @Column
    private String notes;

    @Column  
    private String author;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Steps> steps;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecipeTags> tags;

    @Builder
    public Recipes(Category category, String name, String description, int servings, int caloriesPerServing, String notes, String author) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.caloriesPerServing = caloriesPerServing;
        this.notes = notes;
        this.author = author;
    }

    public String toJson() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
    
        map.put("id", ID);
        map.put("category", category);
        map.put("name", name);
        map.put("description", description);
        map.put("servings", servings);
        map.put("caloriesPerServing", caloriesPerServing);
        map.put("notes", notes);
        map.put("author", author);
        map.put("ingredients", ingredients);
        map.put("steps", steps);
        map.put("tags", tags);
    
        return map;
    }
    
}
