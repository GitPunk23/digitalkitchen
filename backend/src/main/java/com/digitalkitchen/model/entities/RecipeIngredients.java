package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.digitalkitchen.util.MeasurementsDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;


@Entity
@Data
@NoArgsConstructor
@Table(name = "recipe_ingredients")
public class RecipeIngredients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredients ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    @JsonDeserialize(using = MeasurementsDeserializer.class)
    private Measurements measurement;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "notes")
    private String notes;

    @Builder
    public RecipeIngredients(Recipes recipe, Ingredients ingredient, Measurements measurement, Float quantity, String notes) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.measurement = measurement;
        this.quantity = quantity;
        this.notes = notes;
    }
}
