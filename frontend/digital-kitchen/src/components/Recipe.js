import React from 'react';

class Recipe extends React.Component {
  state = {
    recipe: null,
  };

  componentDidMount() {
    // Simulating the response from the server
    const response = 'Recipe [ID=95, category=beef, name=test, description=, servings=-1, caloriesPerServing=-1, notes=, ingredients=[5.0 milligram garlic], steps=[1 test], tags=[]]';

    // Extracting the content between the square brackets
    const recipeString = response.substring(response.indexOf('[') + 1, response.indexOf(']'));
    
    // Splitting the string by comma and equal sign to create key-value pairs
    const keyValuePairs = recipeString.split(',').map(pair => pair.trim().split('='));

    // Creating the recipe object
    const recipe = {};
    keyValuePairs.forEach(pair => {
      const key = pair[0].trim();
      const value = pair[1].trim();
      recipe[key] = value;
    });

    this.setState({ recipe });
  }

  render() {
    const { recipe } = this.state;

    return (
      <div>
        {recipe ? (
          <div>
            <h1>Recipe Details</h1>
            <p>ID: {recipe.ID}</p>
            <p>Category: {recipe.category}</p>
            <p>Name: {recipe.name}</p>
            {/* Render other recipe properties as needed */}
          </div>
        ) : (
          <p>Loading recipe...</p>
        )}
      </div>
    );
  }
}

export default Recipe;
