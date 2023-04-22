package com.example;

import com.database.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            Connection conn = DatabaseManager.getConnection();
            if (conn != null) {
                System.out.println("Connected to database successfully!");

                conn.close();
                System.out.println("Connection to database closed!");
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
