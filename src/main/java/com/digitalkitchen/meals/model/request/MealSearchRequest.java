package com.digitalkitchen.meals.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealSearchRequest {

    private String name;
    private List<String> authors;
    private List<String> recipes;
}
