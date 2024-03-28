package com.digitalkitchen.service;

import com.digitalkitchen.enums.Category;
import com.digitalkitchen.model.entities.*;
import com.digitalkitchen.model.request.RecipeSearchRequest;
import com.digitalkitchen.repository.RecipeRepositoryExtension;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeRepositoryExtensionImpl implements RecipeRepositoryExtension {

    private final EntityManager entityManager;

    public RecipeRepositoryExtensionImpl(final EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public List<Recipe> searchRecipes(RecipeSearchRequest searchParams) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchParams.getName() != null) {
            String name = searchParams.getName();
            final Predicate predicateForName = builder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicateForName);
        }

        if (searchParams.getCategories() != null) {
            List<Category> categories = searchParams.getCategories();
            final Predicate predicateForCategories = builder.equal(root.get("category"), categories);
            predicates.add(predicateForCategories);
        }

        if (searchParams.getAuthors() != null) {
            List<String> authors = searchParams.getAuthors();
            final Predicate predicateForAuthors = builder.equal(root.get("author"), authors);
            predicates.add(predicateForAuthors);
        }

        if (searchParams.getIngredients() != null) {
            List<String> ingredients = searchParams.getIngredients();
            Subquery<RecipeIngredient> subquery = query.subquery(RecipeIngredient.class);
            Root<RecipeIngredient> subRoot = subquery.from(RecipeIngredient.class);
            Join<RecipeIngredient, Recipe> recipeJoin = subRoot.join("recipe");
            subquery.select(recipeJoin.get("id"));
            Predicate ingredientPredicate = subRoot.get("ingredient").in(ingredients);
            subquery.where(ingredientPredicate);
            Predicate predicateForIngredients = builder.exists(subquery);
            predicates.add(predicateForIngredients);
        }

        if (searchParams.getTags() != null) {
            List<String> tags = searchParams.getTags();
            Subquery<RecipeTag> subquery = query.subquery(RecipeTag.class);
            Root<RecipeTag> subRoot = subquery.from(RecipeTag.class);
            Join<RecipeTag, Recipe> recipeJoin = subRoot.join("recipe");
            subquery.select(recipeJoin.get("id"));
            Predicate tagPredicate = subRoot.get("tag").in(tags);
            subquery.where(tagPredicate);
            Predicate predicateForTags = builder.exists(subquery);
            predicates.add(predicateForTags);
        }

        if (searchParams.getServings() != null) {
            List<Integer> servings = searchParams.getServings();
            final Predicate predicateForServings = builder.equal(root.get("servings"), servings);
            predicates.add(predicateForServings);
        }

        if (searchParams.getCalories() != null) {
            List<Integer> calories = searchParams.getCalories();
            final Predicate predicateForCalories = builder.equal(root.get("calories_per_serving"), calories);
            predicates.add(predicateForCalories);
        }

        query.select(root)
                .where(builder.and(predicates.toArray(new Predicate[0])))
                .orderBy(builder.asc(root.get("name")));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
