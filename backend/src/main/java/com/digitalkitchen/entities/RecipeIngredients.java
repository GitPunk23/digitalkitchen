package com.digitalkitchen.entities;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredients ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurements measurement;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "notes")
    private String notes;

    // Getters

    public Recipes getRecipe() {
        return this.recipe;
    }

    public Ingredients getIngredient() {
        return this.ingredient;
    }

    public Measurements getMeasurement() {
        return this.measurement;
    }

    public Float getQuantity() {
        return this.quantity;
    }

    public String getNotes() {
        return this.notes;
    }

    // Setters

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public void setMeasurement(Measurements measurement) {
        this.measurement = measurement;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
