package com.digitalkitchen.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_appliances")
public class RecipeAppliances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "recipe_id")
    private int recipeID;

    @Column(name = "appliance_id")
    private int applianceID;
    
}
