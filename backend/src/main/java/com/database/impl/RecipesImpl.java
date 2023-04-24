package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Recipes;

public class RecipesImpl implements Entity<Recipes> {

    private int ID;
    private String table = "recipes";
    private Recipes recipe;

    /**
     * Creates Recipes Implementation for insertion to the database
     * @param recipe
     */
    public RecipesImpl(Recipes recipe) {
        this.recipe = recipe;
    }

    /**
     * Creates Recipes Implementation for retrieval from the database
     * @param ID
     */
    public RecipesImpl(int ID) {
        this.ID = ID;
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
        int cat = 0, serv = 0, cal = 0;
        String name = null, desc = null, notes = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            cat = resultSet.getInt("category");
            name = resultSet.getString("name");
            desc = resultSet.getString("description");
            serv = resultSet.getInt("servings");
            cal = resultSet.getInt("calories_per_serving");
            notes = resultSet.getString("notes");
        }
        this.recipe = new Recipes(this.ID, cat, name, desc, serv, cal, notes);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.recipe;
    }
}
