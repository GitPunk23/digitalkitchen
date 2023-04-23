package com.database.impl;

import com.entities.RecipeIngredients;

public class RecipeIngredientsImpl implements Entity<RecipeIngredients> {

    private String table = "recipe_ingredients";
    private RecipeIngredients recipeIngredients;

    public RecipeIngredientsImpl(RecipeIngredients recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (recipe_id, ingredient_id, measurement_id, quantity, notes) "
                   + "VALUES " + this.recipeIngredients.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.recipeIngredients.getID() + "';";
        return sql;
    }

    @Override
    public String updateEntityRecordString(String column, String value, String conditionColumn, String conditionValue) {
        String sql = "UPDATE " + this.table + " SET " + column + " = '" + value 
                   + "' WHERE " + conditionColumn + "=" + conditionValue + ";";
        return sql;
    }

    @Override
    public String getEntityRecordString(String conditionColumn, String conditionValue) {
        String sql = "SELECT * FROM " + this.table + " WHERE " + conditionColumn + " = " + conditionValue+";";
        return sql;
    }

    @Override
    public String getAllEntityRecordsString() {
        String sql = "SELECT * FROM " + this.table + ";";
        return sql;
    }
    
}
