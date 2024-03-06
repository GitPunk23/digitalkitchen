package com.digitalkitchen.util;

public class QueryConstants {
    
    public static final String GET_ALL_AUTHORS = "SELECT DISTINCT r.author " + 
                                                 "FROM recipes r";

    public static final String GET_ALL_TAGS = "SELECT t.tag " + 
                                              "FROM Tag t";

    public static final String GET_ALL_INGREDIENTS = "SELECT i.ingredient " +
                                                     "FROM Ingredient i";

}
