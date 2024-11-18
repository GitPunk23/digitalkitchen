package com.digitalkitchen.recipes.service;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.recipes.enums.Category;
import com.digitalkitchen.recipes.model.entities.*;
import com.digitalkitchen.recipes.model.request.RecipeSearchRequest;
import com.digitalkitchen.recipes.repository.RecipeRepositoryExtension;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeRepositoryExtensionImpl implements RecipeRepositoryExtension {

    private final EntityManager entityManager;

    public RecipeRepositoryExtensionImpl(final EntityManager entityManager) { this.entityManager = entityManager; }

    private <T> boolean listContainsElements(List<T> list) {
        return list != null && !list.isEmpty();
    }

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

        if (listContainsElements(searchParams.getCategories())) {
            List<Category> categories = searchParams.getCategories();
            final Predicate predicateForCategories = root.get("category").in(categories);
            predicates.add(predicateForCategories);
        }

        if (listContainsElements(searchParams.getAuthors())) {
            List<String> authors = searchParams.getAuthors();
            Subquery<Author> subquery = query.subquery(Author.class);
            Root<Author> subRoot = subquery.from(Author.class);
            subquery.select(subRoot.get("id"));
            Predicate authorPredicate = subRoot.get("id").in(authors);
            subquery.where(authorPredicate);
            final Predicate predicateForAuthors = root.get("author").get("id").in(authors);
            predicates.add(predicateForAuthors);
        }

        if (listContainsElements(searchParams.getIngredients())) {
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

        if (listContainsElements(searchParams.getTags())) {
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

        if (listContainsElements(searchParams.getServings())) {
            List<Integer> servings = searchParams.getServings();
            final Predicate predicateForServings = root.get("servings").in(servings);
            predicates.add(predicateForServings);
        }

        if (listContainsElements(searchParams.getCalories())) {
            List<Integer> calories = searchParams.getCalories();
            final Predicate predicateForCalories = root.get("caloriesPerServing").in(calories);
            predicates.add(predicateForCalories);
        }

        query.select(root)
                .where(builder.and(predicates.toArray(new Predicate[0])))
                .orderBy(builder.asc(root.get("name")));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
