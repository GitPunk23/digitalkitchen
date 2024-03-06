package com.digitalkitchen.controller.request.transferobjects;

import com.digitalkitchen.model.entities.Steps;

public class StepTransferObject {
    
    private int ID;
    private int stepNumber;
    private String description;

    public StepTransferObject(Steps step) {
        this.ID = step.getID();
        this.stepNumber = step.getStepNumber();
        this.description = step.getDescription();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
