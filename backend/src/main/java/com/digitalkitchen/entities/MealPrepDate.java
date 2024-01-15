package com.digitalkitchen.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meal_prep_date")
public class MealPrepDate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "mealPrepDate", cascade = CascadeType.ALL)
    private List<Meal> meals;
    
    @Column(
            name = "date",
            nullable = false)
    private LocalDate date;
}
