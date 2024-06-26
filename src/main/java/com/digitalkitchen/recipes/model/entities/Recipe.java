package com.digitalkitchen.recipes.model.entities;

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
    private int id;

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

    @Column
    private String author;

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
