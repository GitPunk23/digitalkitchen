import React from 'react';

function Recipe(props) {
  	const { jsonResponse } = props;

  	const {
    	ID,
    	category,
    	name,
    	description,
    	servings,
    	caloriesPerServing,
    	notes,
    	author,
    	ingredients,
    	steps,
    	tags
  	} = jsonResponse;

  	return (
    	<div>
      		<h2>Recipe Details</h2>
      		{/* Render the recipe details using the destructured properties */}
    	</div>
  	);
}

export default Recipe;
