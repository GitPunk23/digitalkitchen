package com.digitalkitchen.model.entities;

import javax.persistence.*;

import com.digitalkitchen.enums.Measurement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column
    @Enumerated(EnumType.STRING)
    private Measurement measurement;

    @Column
    private Float quantity;

    @Column
    private String notes;
}

