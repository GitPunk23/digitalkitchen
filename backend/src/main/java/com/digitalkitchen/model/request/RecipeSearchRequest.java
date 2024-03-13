package com.digitalkitchen.model.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecipeSearchRequest {
    private String name;
    private List<String> categories;
    private List<String> authors;
    private List<String> tags;
    private List<String> ingredients;
    private List<Integer> servings;
    private List<Integer> calories;
}
