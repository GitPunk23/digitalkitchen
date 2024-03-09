package com.digitalkitchen.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.digitalkitchen.enums.Measurement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ingredients")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "recipe_id")
    private int recipeId;

    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private Measurement measurement;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "notes")
    private String notes;
}
