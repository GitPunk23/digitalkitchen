package com.digitalkitchen.model.request;

import com.digitalkitchen.enums.Category;
import com.digitalkitchen.model.entities.Ingredient;
import com.digitalkitchen.model.entities.Tag;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecipeSearchRequest {
    private String name;
    private List<Category> categories;
    private List<String> authors;
    private List<String> tags;
    private List<String> ingredients;
    private List<Integer> servings;
    private List<Integer> calories;
}
