package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Steps;

public class StepsImpl implements Entity<Steps> {

    private int ID;
    private String table = "recipe_tags";
    private Steps step;

    public StepsImpl() {

    }

    public StepsImpl(Steps step) {
        this.step = step;
    }

    public StepsImpl(int ID) {
        this.ID = ID;
    }

    public StepsImpl(StepsImpl base) {

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
        int recipeID = 0, stepNumber = 0;
        String description = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            recipeID = resultSet.getInt("recipe_id");
            stepNumber = resultSet.getInt("step_number");
            description = resultSet.getString("step_description");
            break;
        }
        this.step = new Steps(this.ID, recipeID, stepNumber, description);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.step;
    }

    @Override
    public Entity<Steps> copy() {
        return new StepsImpl(this);
    }
    
}
