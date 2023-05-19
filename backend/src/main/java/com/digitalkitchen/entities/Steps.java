package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name = "steps")
public class Steps {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "step_description")
    private String description;

    // Constructor
    public Steps() {
        
    }

    public Steps(Recipes recipe, int stepNumber, String description) {
        this.recipe = recipe;
        this.stepNumber = stepNumber;
        this.description = description;
    }
    
    // Getters
    public int getID() {
        return this.ID;
    }

    public Recipes getRecipe() {
        return recipe;
    }
    
    public int getStepNumber() {
        return stepNumber;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters
    
    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }
    
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        String out = "";
        out += stepNumber + " " + description;
        return out;
    }

    
}