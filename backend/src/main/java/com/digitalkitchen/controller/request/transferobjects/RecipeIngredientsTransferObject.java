package com.digitalkitchen.controller.request.transferobjects;

import com.digitalkitchen.model.entities.RecipeIngredients;

public class RecipeIngredientsTransferObject {

    private String ingredient;
    private String measurement;
    private Float quantity;
    private String notes;

    public RecipeIngredientsTransferObject(RecipeIngredients ingredient) {
        this.ingredient = ingredient.getIngredient().getIngredient();
        this.measurement = ingredient.getMeasurement().getMeasurement();
        this.quantity = ingredient.getQuantity();
        this.notes = ingredient.getNotes();
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
