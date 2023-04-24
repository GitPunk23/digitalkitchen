package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Category;

public class CategoryImpl implements Entity<Category> {

    private int ID;
    private String table = "recipe_tags";
    private Category category;

    /**
     * Creates Default Category Implementation
     */
    public CategoryImpl() {

    }

    /**
     * Creates Category Implementation for insertion to the database
     * @param category
     */
    public CategoryImpl(Category category) {
        this.category = category;
    }

    /**
     * Creates Category Implementation for retrieval from the database
     * @param ID
     */
    public CategoryImpl(int ID) {
        this.ID = ID;
    }

    /**
     * Creates default copy of Category Implementation
     * @param base
     */
    public CategoryImpl(CategoryImpl base) {

    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (name) "
                   + "VALUES " + this.category.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.category.getID() + "';";
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
        String name = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            name = resultSet.getString("name");
            break;
        }
        this.category = new Category(this.ID, name);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.category;
    }

    @Override
    public Entity<Category> copy() {
        return new CategoryImpl(this);
    }
    
}
