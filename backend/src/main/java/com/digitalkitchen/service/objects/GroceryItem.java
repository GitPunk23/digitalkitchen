package com.digitalkitchen.service.objects;

import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Measurements;

public class GroceryItem {
    
    private Ingredients ingredient;
    private float quantity;
    private Measurements measurement;
    
    public GroceryItem() {

    }

    public GroceryItem(Ingredients ingredient, float quantity, Measurements measurement) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Measurements getMeasurement() {
        return measurement;
    }
    
    public void setMeasurement(Measurements measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "GroceryItem [ingredient=" + ingredient + ", quantity=" + quantity + ", measurement=" + measurement
                + "]";
    }
}
