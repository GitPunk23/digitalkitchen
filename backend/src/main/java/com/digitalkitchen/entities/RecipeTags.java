package com.digitalkitchen.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.digitalkitchen.util.TagsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "recipe_tags")
public class RecipeTags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipes recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @JsonDeserialize(using = TagsDeserializer.class)
    private Tags tag;

    //Constructor
    public RecipeTags(Recipes recipe, Tags tag) {
        this.recipe = recipe;
        this.tag = tag;
    }

    // Getters

    public int getID() {
    return ID;
    }
    
    public Recipes getRecipe() {
    return recipe;
    }
    
    public Tags getTag() {
    return tag;
    }
    
    // Setters
    
    public void setTag(Tags tag) {
    this.tag = tag;
    }

    @Override
    public String toString() {
        return tag.getName();
    }

}
