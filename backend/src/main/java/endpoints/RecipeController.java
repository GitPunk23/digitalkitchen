package endpoints;

import com.entities.*;
import com.database.DatabaseManager;
import com.database.impl.*;

public class RecipeController {

    private DatabaseManager databaseManager;

    public RecipeController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void createRecipe() {
        //Process JSON

        //Create recipe and recipe implementation
            //Sanitize Inputs?

        //Create through DBM
            //Save Recipe ID

        //Create Ingredients, Steps, and Tags lists with Recipe ID
            //Check for new ingredients
            //Check for new tags

        //Create Impl for each object

        //Create each object through DBM

        //Return HTML Response
        
    }

}
