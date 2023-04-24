package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Ingredients;

public class IngredientsImpl implements Entity<Ingredients> {

    private int ID;
    private String table = "ingredients";
    private Ingredients ingredient;

    public IngredientsImpl() {

    }

    public IngredientsImpl(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientsImpl(int ID) {
        this.ID = ID;
    }

    public IngredientsImpl(IngredientsImpl base) {
        
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (ingredient) "
                   + "VALUES " + this.ingredient.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.ingredient.getID() + "';";
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
        String ingredient = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            ingredient = resultSet.getString("ingredient");
            break;
        }
        this.ingredient = new Ingredients(this.ID, ingredient);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.ingredient;
    }

    @Override
    public Entity<Ingredients> copy() {
        return new IngredientsImpl(this);
    }
    
}
