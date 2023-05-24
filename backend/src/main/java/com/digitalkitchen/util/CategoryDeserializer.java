package com.digitalkitchen.util;

import java.io.IOException;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.service.CategoryService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CategoryDeserializer extends JsonDeserializer<Category> {
    @Autowired
    private CategoryService categoryService;

    @Override
    public Category deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int categoryId = Integer.parseInt(p.getValueAsString());
        return categoryService.getCategoryById(categoryId).orElse(null);
    }
}

