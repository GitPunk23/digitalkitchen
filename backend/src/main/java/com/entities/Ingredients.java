package com.entities;

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

    /**
     * Constructor for insertion
     * @param name
     */
    public Ingredients(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Constructor for retrieval
     * @param ID
     * @param name
     */
    public Ingredients(int ID, String ingredient) {
        this.ID = ID;
        this.ingredient = ingredient;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "('"+ this.ingredient +"')";
        return sql;
    }

}
