package com.digitalkitchen.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.NONE)
public class RecipeRequest {

    @JsonProperty("recipes")
    @Valid
    @Size(min = 1, message = "At least one recipe is required")
    private List<RecipeRequestInfo> recipes;
}
