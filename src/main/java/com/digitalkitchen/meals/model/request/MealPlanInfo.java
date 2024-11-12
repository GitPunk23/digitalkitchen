package com.digitalkitchen.meals.model.request;

import com.digitalkitchen.meals.model.entities.MealPlan;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class MealPlanInfo {

    private String id;

    private String nickname;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;

    @NotBlank
    private String relationId;

    public boolean isCopyOfMealPlan(MealPlan mealPlan) {
        return mealPlan.getNickname().equals(this.nickname) &&
                mealPlan.getStartDate().equals(this.startDate) &&
                mealPlan.getEndDate().equals(this.endDate);
    }

}
