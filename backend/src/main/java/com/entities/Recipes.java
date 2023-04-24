package com.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name = "recipes")
public class Recipes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private int categoryID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "servings")
    private int servings;

    @Column(name = "calories_per_serving")
    private int caloriesPerServing;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<Steps> steps;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<RecipeTags> tags;
    
    /**
     * Creates Recipe to be inserted into the database
     * @param categoryID Category ID
     * @param name Name of the recipe
     * @param description Description of the recipe
     * @param servings Number of servings
     * @param caloriesPerServing Calories per serving
     * @param notes Notes for the recipe
     */
    public Recipes(int categoryID, String name, String description, int servings, int caloriesPerServing,
                    String notes) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.caloriesPerServing = caloriesPerServing;
        this.notes = notes;
    }

    /**
     * Creates recipe retrieved from the database
     * @param ID
     * @param categoryID
     * @param name
     * @param description
     * @param servings
     * @param caloriesPerServing
     * @param notes
     * @throws SQLException
     */
    public Recipes(int ID, int categoryID, String name, String description, int servings, 
                   int caloriesPerServing, String notes) throws SQLException {
        this.ID = ID;
        this.categoryID = categoryID; 
        this.name = name;
        this.description = description; 
        this.servings = servings;
        this.caloriesPerServing = caloriesPerServing; 
        this.notes = notes;
    }
    
    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ this.categoryID + ", '" + this.name + "', '" + this.description + "', "
                    + this.servings + ", " + this.caloriesPerServing + ", '" + this.notes +"')";
        return sql;
    }

}
