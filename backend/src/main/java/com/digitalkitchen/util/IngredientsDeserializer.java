package com.digitalkitchen.util;

import java.io.IOException;

import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.service.IngredientsService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class IngredientsDeserializer extends JsonDeserializer<Ingredients> {
    @Autowired
    private IngredientsService ingredientsService;

    @Override
    public Ingredients deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String ingredient = p.getValueAsString();
        return ingredientsService.getIngredientByName(ingredient).orElse(null);
    }
}

