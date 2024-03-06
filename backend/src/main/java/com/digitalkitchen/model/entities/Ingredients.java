package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "ingredient")
    private String ingredient;

    public Ingredients(String ingredient) {
        this.ingredient = ingredient;
    }

}
