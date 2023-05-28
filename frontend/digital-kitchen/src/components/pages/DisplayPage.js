import React, { Component } from 'react';
import RecipeDisplay from '../objects/RecipeDisplay';

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
