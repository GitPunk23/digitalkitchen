package com.api.request;

import com.entities.Steps;

public class StepsRequest {

    private int stepNumber;
    private String description;

    public StepsRequest(int stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public Steps createStep(int recipeID) {
        Steps step = new Steps(recipeID, this.stepNumber, this.description);
        return step;
    }
}
