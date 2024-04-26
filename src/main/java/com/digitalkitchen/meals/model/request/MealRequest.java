package com.digitalkitchen.meals.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import java.util.List;

import static com.digitalkitchen.meals.util.Constants.AT_LEAST_ONE_MEAL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealRequest {

    @JsonProperty("plan")
    @Valid
    private MealRequestPlanInfo plan;

    @JsonProperty("meals")
    @Valid
    @Size(min = 1, message = AT_LEAST_ONE_MEAL)
    private List<MealRequestMealInfo> meals;
}
