package com.digitalkitchen.meals.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealRequest {

    @JsonProperty("plan")
    @Valid
    private MealPlanInfo plan;

    @JsonProperty("records")
    @Valid
    private List<MealRecordInfo> records;

    @JsonProperty("meals")
    @Valid
    private List<MealInfo> meals;

    public boolean isEmpty() {
        return this.plan == null &&
                this.records.isEmpty() &&
                this.meals.isEmpty();
    }
}
