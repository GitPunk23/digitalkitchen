package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Data
@NoArgsConstructor
@Table(name = "steps")
public class Steps {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "step_description")
    private String description;

    public Steps(Recipes recipe, int stepNumber, String description) {
        this.recipe = recipe;
        this.stepNumber = stepNumber;
        this.description = description;
    }
}