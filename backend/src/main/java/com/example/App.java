package com.example;

import com.database.DatabaseManager;
import com.database.impl.RecipesImpl;
import com.entities.Recipes;

import endpoints.RecipeController;
import java.sql.Connection;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
        DatabaseManager dbManager = new DatabaseManager();
        RecipeController recipeController = new RecipeController(dbManager);

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
