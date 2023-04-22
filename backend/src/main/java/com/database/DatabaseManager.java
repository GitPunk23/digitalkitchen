package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.database.impl.Entity;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://192.168.1.136:3306/recipes";
    private static final String USER = "recipes";
    private static final String PASSWORD = "password";
    
    private static Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        } 
    }

    public void executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        statement.close();
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close database connection.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Failed to get database connection: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    //CRUD OPERATIONS

    /**
     * This method adds a record to an entity in the database
     * @throws SQLException
     * @returns ID of the newly created record
     */
    public <T> Long createEntityRecord(Entity<T> entity) throws SQLException {
        String sql = entity.createEntityRecordString();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        //Return ID of newly created record
        ResultSet resultSet = statement.getGeneratedKeys();
        return resultSet.getLong(1);
    }

    /**
     * This method adds a record to an entity in the database
     */
    public <T> void deleteEntityRecord(Entity<T> entity) {

    }

    /**
     * This method removes a record from an entity in the database
     */
    public <T> void updateEntityRecord(Entity<T> entity) {

    }

    /**
     * This method retrieves a record from an entity in the database
     */
    public <T> T getEntityRecord(Entity<T> entity) {
        return null;
    }

    /**
     * This method retrieves all records from an entity in the database
     */
    public <T> List<T> getAllEntityRecords(Entity<T> entity) {
        return null;
    }
}

