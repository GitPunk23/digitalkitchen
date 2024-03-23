package com.digitalkitchen.util;

public class QueryConstants {
    
    public static final String GET_ALL_AUTHORS = "SELECT DISTINCT r.author " + 
                                                 "FROM Recipe r";

    public static final String GET_ALL_TAGS = "SELECT t.tag " + 
                                              "FROM Tag t";

    public static final String GET_ALL_INGREDIENTS = "SELECT i.ingredient " +
                                                     "FROM Ingredient i";

    public static final String SEARCH_RECIPES =
            "SELECT * FROM recipes r " +
                    "JOIN recipe_tags rt ON r.id = rt.recipe_id " +
                    "JOIN recipe_ingredients ri ON r.id = ri.recipe_id " +
                    "WHERE (:name IS NULL OR LOWER(r.name) LIKE CONCAT('%', LOWER(:name), '%')) " +
                    "OR (:categories IS NULL OR r.category IN :categories) " +
                    "OR (:authors IS NULL OR LOWER(r.author) IN :authors) " +
                    "OR (:tags IS NULL OR rt.tag_id IN (SELECT id FROM tags WHERE name IN :tags)) " +
                    "OR (:ingredients IS NULL OR ri.ingredient_id IN (SELECT id FROM ingredients WHERE name IN :ingredients)) " +
                    "OR (:servings IS NULL OR r.servings IN :servings) " +
                    "OR (:calories IS NULL OR r.calories_per_serving IN :calories)";










}
