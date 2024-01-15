import MealList from "../MealsList";
import FetchManager from "../util/FetchManager";
import React, { useState, useEffect } from 'react';

useEffect(async () => {
    const response = await FetchManager.fetchMeals();
    console.log(response);
});

const MealPlannerPage = () => {
    return (
        <div>
            <h1>Meal Planner</h1>
            <div>
                <MealList ></MealList>
            </div>
        </div>
    );
}

export default MealPlannerPage;