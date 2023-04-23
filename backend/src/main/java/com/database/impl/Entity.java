package com.database.impl;

import java.util.List;


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
    public String getEntityRecordString(String conditionColumn, String conditionValue);

    /**
     * This method creates a SQL statement that retrieves all records from an entity in the database
     * @return A string containing a SQL statement
     */
    public String getAllEntityRecordsString();
    
}
