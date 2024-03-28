package com.digitalkitchen.model.request;

import com.digitalkitchen.enums.Category;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecipeSearchRequest {
    private String name;
    private List<Category> categories;
    private List<String> authors;
    private List<String> tags;
    private List<String> ingredients;
    private List<Integer> servings;
    private List<Integer> calories;
}
