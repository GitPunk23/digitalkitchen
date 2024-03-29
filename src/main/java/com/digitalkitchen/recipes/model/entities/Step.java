package com.digitalkitchen.recipes.model.entities;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "steps")
public class Step {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    @Column
    private int stepNumber;

    @Column
    private String description;
}