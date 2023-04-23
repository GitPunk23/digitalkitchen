package com.entities;

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

    private int recipeID;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "step_description")
    private String description;

    public Steps(int recipeID, int stepNumber, String description) {
        this.recipeID = recipeID;
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ this.recipeID + ", " + this.stepNumber + ", '" + this.description +"')";
        return sql;
    }

}