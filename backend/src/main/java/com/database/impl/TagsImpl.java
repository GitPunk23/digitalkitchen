package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Tags;

public class TagsImpl implements Entity<Tags> {

    private int ID;
    private String table = "tags";
    private Tags tag;

    public TagsImpl(Tags tag) {
        this.tag = tag;
    }

    public TagsImpl(int ID) {
        this.ID = ID;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (tag) "
                   + "VALUES " + this.tag.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.tag.getID() + "';";
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
        String tag = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            tag = resultSet.getString("tag");
        }
        this.tag = new Tags(this.ID, tag);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.tag;
    }
    
}
