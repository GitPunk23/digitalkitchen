package com.digitalkitchen.meals.service;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.meals.model.entities.*;
import com.digitalkitchen.meals.model.request.MealSearchRequest;
import com.digitalkitchen.meals.repository.MealRepositoryExtension;
import com.digitalkitchen.recipes.model.entities.Recipe;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class MealRepositoryExtensionImpl implements MealRepositoryExtension {

    private final EntityManager entityManager;

    public MealRepositoryExtensionImpl(final EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public List<Meal> searchMeals(MealSearchRequest searchParams) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Meal> query = builder.createQuery(Meal.class);
        Root<Meal> root = query.from(Meal.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchParams.getName() != null) {
            String name = searchParams.getName();
            final Predicate predicateForName = builder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicateForName);
        }

        if (!isEmpty(searchParams.getAuthors())) {
            List<String> authors = searchParams.getAuthors();
            Subquery<Author> subquery = query.subquery(Author.class);
            Root<Author> subRoot = subquery.from(Author.class);
            subquery.select(subRoot.get("id"));
            Predicate authorPredicate = subRoot.get("id").in(authors);
            subquery.where(authorPredicate);
            final Predicate predicateForAuthors = root.get("author").get("id").in(authors);
            predicates.add(predicateForAuthors);
        }

        if (!isEmpty(searchParams.getRecipes())) {
            List<String> recipeIds = searchParams.getRecipes();
            Subquery<MealRecipe> subquery = query.subquery(MealRecipe.class);
            Root<MealRecipe> subRoot = subquery.from(MealRecipe.class);
            Join<MealRecipe, Meal> mealJoin = subRoot.join("meal");
            subquery.select(mealJoin.get("id"));
            Predicate recipePredicate = subRoot.get("recipe").get("id").in(recipeIds);
            subquery.where(recipePredicate);
            Predicate predicateForRecipes = builder.exists(subquery);
            predicates.add(predicateForRecipes);
        }

        query.select(root)
                .where(builder.and(predicates.toArray((new Predicate[0]))))
                .orderBy(builder.asc(root.get("name")));

        return entityManager.createQuery(query)
                .getResultList();

    }
}