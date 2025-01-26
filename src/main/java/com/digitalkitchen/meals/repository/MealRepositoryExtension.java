package com.digitalkitchen.meals.repository;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.request.MealSearchRequest;

import java.util.List;

public interface MealRepositoryExtension {

    List<Meal> searchMeals(MealSearchRequest searchParams);
}
