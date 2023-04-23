package com.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;


@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    private int recipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredients ingredient;

    private int ingredientID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurements measurement;

    private int measurementID;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "notes")
    private String notes;

    public RecipeIngredients(int recipeID, int ingredientID, int measurementID, float quantity, String notes) {
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.measurementID = measurementID;
        this.quantity = quantity; 
        this.notes = notes;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ this.recipeID + ", " + this.ingredientID + ", " 
                   + this.measurementID + ", " + this.quantity + ", '" + this.notes + "')";
        return sql;
    }
}
