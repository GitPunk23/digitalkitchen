package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.RecipeTags;

public class RecipeTagsImpl implements Entity<RecipeTags> {

    private int ID;
    private String table = "recipe_tags";
    private RecipeTags recipeTags;

    public RecipeTagsImpl() {

    }

    public RecipeTagsImpl(RecipeTags recipeTags) {
        this.recipeTags = recipeTags;
    }

    public RecipeTagsImpl(int ID) {
        this.ID = ID;
    }

    public RecipeTagsImpl(RecipeTagsImpl base) {

    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (recipe_id, tag_id) "
                   + "VALUES " + this.recipeTags.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.recipeTags.getID() + "';";
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
        int recipeID = 0, tagID = 0;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            recipeID = resultSet.getInt("recipe_id");
            tagID = resultSet.getInt("tag_id");
            break;
        }
        this.recipeTags = new RecipeTags(this.ID, recipeID, tagID);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.recipeTags;
    }

    @Override
    public Entity<RecipeTags> copy() {
        return new RecipeTagsImpl(this);
    }
    
}
