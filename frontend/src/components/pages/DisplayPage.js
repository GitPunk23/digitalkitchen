import React from 'react';
import RecipeDisplay from '../objects/RecipeDisplay';

function DisplayPage(props) {
  	const displayRecipe = (record) => {
    	return (
      		<div>
        		<RecipeDisplay formData={record} />
      		</div>
    	);
  	};

  	const { data } = props;
  		console.log("Displaying: ", data);

  	return (
    	<div>
      		{displayRecipe(data)}
    	</div>
  	);
}

export default DisplayPage;
