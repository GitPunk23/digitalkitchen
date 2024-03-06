package com.digitalkitchen.model.request;

import org.springframework.stereotype.Component;

import com.digitalkitchen.model.entities.Recipes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIncludeProperties(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RecipeRequest {
    //TODO:: CREATE REQUEST OBJECT LIKE WORK

    private Recipes recipe;
}
