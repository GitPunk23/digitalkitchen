package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.RecipeIngredients;

public class RecipeIngredientsImpl implements Entity<RecipeIngredients> {

    private int ID;
    private String table = "recipe_ingredients";
    private RecipeIngredients recipeIngredients;

    public RecipeIngredientsImpl() {

    }

    public RecipeIngredientsImpl(RecipeIngredients recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeIngredientsImpl(int ID) {
        this.ID = ID;
    }

    public RecipeIngredientsImpl(RecipeIngredientsImpl base) {

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
    public String getEntityRecordString() {
        String sql = "SELECT * FROM " + this.table + " WHERE id = '" + this.ID+"';";
        return sql;
    }

    @Override
    public String getAllEntityRecordsString() {
        String sql = "SELECT * FROM " + this.table + ";";
        return sql;
    }

    @Override
    public void createEntityRecordObject(ResultSet resultSet) throws SQLException {
        int recipeID = 0, ingredientID = 0, measurementID = 0;
        float quantity = 0;
        String notes = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            recipeID = resultSet.getInt("recipe_id");
            ingredientID = resultSet.getInt("ingredient_id");
            measurementID = resultSet.getInt("measurement_id");
            quantity = resultSet.getInt("quantity");
            notes = resultSet.getString("notes");
            break;
        }
        this.recipeIngredients = new RecipeIngredients(this.ID, recipeID, ingredientID, measurementID, quantity, notes);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.recipeIngredients;
    }

    @Override
    public Entity<RecipeIngredients> copy() {
        return new RecipeIngredientsImpl(this);
    }
    
}
