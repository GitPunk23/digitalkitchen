import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import RecipeDisplay from './objects/RecipeDisplay';

function SearchResultsDisplay(props) {
  	const [currentRecipeID, setCurrentRecipeID] = useState(props.currentRecipeID);
  	const [recipes, setRecipes] = useState(props.recipes);

  	useEffect(() => {
    	if (props.currentRecipeID !== currentRecipeID) {
      		setCurrentRecipeID(props.currentRecipeID);
    	}
  	}, [props.currentRecipeID]);

  	const handlePrevClick = () => {
    	if (currentRecipeID > 0) {
      		setCurrentRecipeID(prevState => prevState - 1);
    	}
  	};

  	const handleNextClick = () => {
    	if (currentRecipeID < recipes.length - 1) {
      		setCurrentRecipeID(prevState => prevState + 1);
    	}
  	};

	const updateRecipe = (recipeData) => {
		//console.log(recipeData);
	};

  	return (
    	<div key={currentRecipeID}>
      		<Button onClick={handlePrevClick}>{'<'}</Button>
      		<Button onClick={handleNextClick}>{'>'}</Button>
      		<RecipeDisplay 
				formData={recipes[currentRecipeID]} 
				updateRecipe={updateRecipe}/>
    	</div>
  	);
}

export default SearchResultsDisplay;
