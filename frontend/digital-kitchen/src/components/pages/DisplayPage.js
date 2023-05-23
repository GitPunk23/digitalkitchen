import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import Recipe from '../objects/Recipe';
//import RecipeDisplay from './RecipeDisplay'; // Import the RecipeDisplay component

class DisplayPage extends Component {

  render() {
    const { data } = this.props;
    console.log("Displaying: ",data);

    return (
      <div>
        <h2>Recipe Details</h2>
        <p>{data.name}</p>
      </div>
    );
  }
}

export default DisplayPage;
