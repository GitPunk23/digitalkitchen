package com.digitalkitchen.meals.model.entities;

import com.digitalkitchen.recipes.model.entities.Recipe;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "meal_recipes")
public class MealRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id")
    @JsonBackReference
    private Meal meal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

}
