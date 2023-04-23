package com.database.impl;

import com.entities.Steps;

public class StepsImpl implements Entity<Steps> {

    private String table = "recipe_tags";
    private Steps step;

    public StepsImpl(Steps step) {
        this.step = step;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (recipe_id, step_number, step_description) "
                   + "VALUES " + this.step.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.step.getID() + "';";
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
