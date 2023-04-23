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

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "('"+ this.ingredient +"')";
        return sql;
    }

}
