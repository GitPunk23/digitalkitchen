package com.database.impl;

import com.entities.Category;

public class CategoryImpl implements Entity<Category> {

    private String table = "recipe_tags";
    private Category category;

    public CategoryImpl(Category category) {
        this.category = category;
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
    public String deleteEntityRecordString(String column, String value) {
        String sql = "DELETE FROM " + this.table + " WHERE " + column + "='" + value + "';";
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
