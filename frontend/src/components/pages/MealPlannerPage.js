import MealList from "../MealsList";
import MealPlannerForm from "../MealPlannerForm";
import FetchManager from "../util/FetchManager";
import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';

//useEffect(async () => {
    //const response = await FetchManager.fetchMeals();
    //console.log(response);
//});

const emptyMealPlan = {
    date: '',
    name: '',
    type: '',
    notes: '',	
    tags: [],
    recipes: []
};

const form = true;

const MealPlannerPage = () => {
    return (
        <div>
            <h1>Meal Planner</h1>
            { !form ? (
            <div>
                <MealList></MealList>
            </div>
             ) : (
            <div>
                <MealPlannerForm
                    mealPlanFormat={emptyMealPlan}
                ></MealPlannerForm>
            </div>
             )}
            <div>
                <Button>Plan a Meal</Button>
            </div>
        </div>
    );
}

export default MealPlannerPage;