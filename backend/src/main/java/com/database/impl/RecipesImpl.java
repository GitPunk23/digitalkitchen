package com.database.impl;

import com.entities.Recipes;

public class RecipesImpl implements Entity<Recipes> {

    private String table = "recipes";
    private Recipes recipe;

    public RecipesImpl(Recipes recipe) {
        this.recipe = recipe;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() { 
        String sql = "INSERT INTO " + this.table + " (category_id, name, description, servings, calories_per_serving, notes) "
                   + "VALUES " + this.recipe.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.recipe.getID() + "';";
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
