package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "ingredients")
public class Ingredients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "ingredient")
    private String ingredient;

    public Ingredients() {
        
    }

    public Ingredients(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getID() {
        return this.ID;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String toString() {
        String out = "";
        out += getIngredient();
        return out;
    }
}
