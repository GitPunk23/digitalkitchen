package com.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name = "recipes")
public class Recipes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "servings")
    private int servings;

    @Column(name = "calories_per_serving")
    private int caloriesPerServing;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<Steps> steps;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<RecipeTags> tags;
    
    public Long getID() {
        return this.ID;
    }

    public String getValues() {
        String sql = "("+ /*this.category.getID() + ", " +*/ this.name + ", " + this.description + ", "
                    + this.servings + ", " + this.caloriesPerServing + ", " + this.notes +")";
        return sql;
    }

}
