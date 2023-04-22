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
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "step_description")
    private String description;

    public Long getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ this.recipe.getID() + ", " + this.stepNumber + ", " + this.description +")";
        return sql;
    }

}