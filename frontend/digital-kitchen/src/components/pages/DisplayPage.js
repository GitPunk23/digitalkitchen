import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import Recipe from '../objects/Recipe';
import RecipeDisplay from '../objects/RecipeDisplay';
//import RecipeDisplay from './RecipeDisplay'; // Import the RecipeDisplay component

class DisplayPage extends Component {
  
  displayRecipe = (record) => {

    

    return (
      <div>
        <RecipeDisplay formData={record}/>
      </div>
    )
  }

  render() {
    const { data } = this.props;
    console.log("Displaying: ",data);

    return (
      <div>
        {this.displayRecipe(data)}
      </div>
    );
  }
}

export default DisplayPage;
