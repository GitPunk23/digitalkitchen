package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity<T> {

    /**
     * This method retrieves the table relevant to the entity
     * @return String object containing table name
     */
    public String getTable();

    /*
     * CRUD OPERATIONS
     */

    /**
     * This method creates a SQL statement that adds a record to an entity in the database
     * @return A string containing a SQL statement
     */
    public String createEntityRecordString();

    /**
     * This method creates a SQL statement that removes a record from an entity in the database
     * @return A string containing a SQL statement
     */
    public String deleteEntityRecordString();

    /**
     * This method creates a SQL statement that updates a record in an entity in the database
     * @return A string containing a SQL statement
     */
    public String updateEntityRecordString(String column, String value, String conditionColumn, String conditionValue);

    /**
     * This method creates a SQL statement that retrieves a record from an entity in the database
     * @return A string containing a SQL statement
     */
    public String getEntityRecordString();

    /**
     * This method creates a SQL statement that retrieves all records from an entity in the database
     * @return A string containing a SQL statement
     */
    public String getAllEntityRecordsString();

    /**
     * This method creates a record object from data retrieved from the database
     *  @param resultSet The resultset from the database
     * @throws SQLException
     */
    public void createEntityRecordObject(ResultSet resultSet) throws SQLException;

    /**
     * This method returns the record object held by this entity implementation
     * @param <T>
     * @return
     */
    public <T> T getEntityRecordObject();



    
}
