package com.digitalkitchen.util;

public class QueryConstants {
    
    public static final String GET_ALL_AUTHORS = "SELECT DISTINCT r.author " + 
                                                 "FROM recipes r";

    public static final String GET_ALL_TAGS = "SELECT t.tag " + 
                                              "FROM Tag t";

    public static final String GET_ALL_INGREDIENTS = "SELECT i.ingredient " +
                                                     "FROM Ingredient i";

    public static final String SEARCH_RECIPES = "SELECT r FROM Recipe r " +
                                                      "WHERE (:name IS NULL OR r.name LIKE CONCAT('%', :name, '%')) " +
                                                      "AND (:categories IS NULL OR r.category.name = :categories) " +
                                                      "AND (:authors IS NULL OR r.author = :authors) " +
                                                      "AND (:tags IS NULL OR :tags MEMBER OF r.tags) " +
                                                      "AND (:ingredients IS NULL OR :ingredients MEMBER OF r.ingredients) " +
                                                      "AND (:servings IS NULL OR r.servings = :servings) " +
                                                      "AND (:calories IS NULL OR r.caloriesPerServing = :calories)";
                                             

}
