package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.Column;


@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Recipes> recipes;

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public List<Recipes> getRecipes() {
        return this.recipes;
    }

    @Override
    public String toString() {
        String out = "ID: ";
        out += ID;
        out += " category: ";
        out += name;
        return out;
    }

}