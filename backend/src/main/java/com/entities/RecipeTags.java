package com.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
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

    private int recipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tags tag;

    private int tagID;

    public RecipeTags(int recipeID, int tagID) {
        this.recipeID = recipeID;
        this.tagID = tagID;
    }

    public RecipeTags(int ID, int recipeID, int tagID) {
        this.ID = ID;
        this.recipeID = recipeID;
        this.tagID = tagID;
    }

    public int getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ this.recipeID + ", " + this.tagID  +")";
        return sql;
    }

}
