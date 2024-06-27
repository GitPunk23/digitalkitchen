package com.digitalkitchen.meals.model.entities;

import com.digitalkitchen.meals.enums.MealType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "meal_records")
public class MealRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    @JsonBackReference
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    @JsonBackReference
    private MealPlan mealPlan;

    @Column
    private LocalDate date;

    @Column
    @Enumerated(EnumType.STRING)
    private MealType type;
}
