package com.digitalkitchen.recipes.model.entities;

import com.digitalkitchen.authors.model.entities.Author;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import com.digitalkitchen.recipes.enums.Category;

import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "recipes")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int servings;

    @Column
    private int caloriesPerServing;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Step> steps;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<RecipeTag> tags;
}
