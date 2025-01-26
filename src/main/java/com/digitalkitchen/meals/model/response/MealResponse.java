package com.digitalkitchen.meals.model.response;

import com.digitalkitchen.enums.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MealResponse {

    @JsonProperty("status")
    public ResponseStatus status;

    @JsonProperty("message")
    public String message;

    @JsonProperty("meals")
    private final List<MealResponseInfo> meals;
}
