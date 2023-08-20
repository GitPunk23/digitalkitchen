import React from 'react';

class Recipe extends React.Component {
  	constructor(props) {
		super(props);
		const { jsonResponse } = props;

		this.state = {
			ID: jsonResponse.id,
			category: jsonResponse.category,
			name: jsonResponse.name,
			description: jsonResponse.description,
			servings: jsonResponse.servings,
			caloriesPerServing: jsonResponse.caloriesPerServing,
			notes: jsonResponse.notes,
			author: jsonResponse.author,
			ingredients: jsonResponse.ingredients,
			steps: jsonResponse.steps,
			tags: jsonResponse.tags
		};
  	}

	render() {
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
		} = this.state;

		return (
			<div>
				<h2>Recipe Details</h2>
				{/* Render the recipe details using the state properties */}
			</div>
		);
  	}
}

export default Recipe;
