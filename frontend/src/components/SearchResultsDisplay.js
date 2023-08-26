import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import DisplayPage from './pages/DisplayPage';

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

  	return (
    	<div key={currentRecipeID}>
      		<Button onClick={handlePrevClick}>{'<'}</Button>
      		<Button onClick={handleNextClick}>{'>'}</Button>
      		<DisplayPage data={recipes[currentRecipeID]} />
    	</div>
  	);
}

export default SearchResultsDisplay;
