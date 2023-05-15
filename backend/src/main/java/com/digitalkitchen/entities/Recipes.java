package com.digitalkitchen.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.transaction.Transactional;

@Entity(name = "recipes")
@Table(name = "recipes",
       uniqueConstraints = {
            @UniqueConstraint(name = "recipe_name_unique", columnNames = "name")
       })
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
    private Category category;

    @Column(
            name = "name",
            nullable = false,
            unique = true)
    private String name;

    @Column(
            name = "description",
            nullable = true)
    private String description;

    @Column(
            name = "servings",
            nullable = true)
    private int servings;

    @Column(
            name = "calories_per_serving",
            nullable = true)
    private int caloriesPerServing;

    @Column(
            name = "notes",
            nullable = true)
    private String notes;

    @Column(
            name = "author",
            nullable = true)    
    private String author;

    @OneToMany(mappedBy = "recipe", 
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipe", 
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<Steps> steps;

    @OneToMany(mappedBy = "recipe", 
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<RecipeTags> tags;

    public Recipes() {

    }

    public Recipes(Category category, String name, String description, int servings, int caloriesPerServing, String notes) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.caloriesPerServing = caloriesPerServing;
        this.notes = notes;
    }

    // Getters

public int getID() {
    return ID;
    }
    
    public Category getCategory() {
    return category;
    }
    
    public String getName() {
    return name;
    }
    
    public String getDescription() {
    return description;
    }
    
    public int getServings() {
    return servings;
    }
    
    public int getCaloriesPerServing() {
    return caloriesPerServing;
    }
    
    public String getNotes() {
    return notes;
    }

    public String getAuthor() {
        return author;
    }
    
    @Transactional
    public List<RecipeIngredients> getIngredients() {
    return ingredients;
    }
    
    public List<Steps> getSteps() {
    return steps;
    }
    
    public List<RecipeTags> getTags() {
    return tags;
    }
    
    // Setters
    
    public void setCategory(Category category) {
    this.category = category;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public void setDescription(String description) {
    this.description = description;
    }
    
    public void setServings(int servings) {
    this.servings = servings;
    }
    
    public void setCaloriesPerServing(int caloriesPerServing) {
    this.caloriesPerServing = caloriesPerServing;
    }
    
    public void setNotes(String notes) {
    this.notes = notes;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIngredients(List<RecipeIngredients> ingredients) {
    this.ingredients = ingredients;
    }
    
    public void setSteps(List<Steps> steps) {
    this.steps = steps;
    }
    
    public void setTags(List<RecipeTags> tags) {
    this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe [")
            .append("ID=").append(ID)
            .append(", category=").append(category.getName())
            .append(", name=").append(name)
            .append(", description=").append(description)
            .append(", servings=").append(servings)
            .append(", caloriesPerServing=").append(caloriesPerServing)
            .append(", notes=").append(notes)
            .append(", ingredients=").append(ingredients)
            .append(", steps=").append(steps)
            .append(", tags=").append(tags)
            .append("]");
        return sb.toString();
    }    
}
