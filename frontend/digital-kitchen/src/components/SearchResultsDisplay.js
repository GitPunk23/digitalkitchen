import React from 'react';
import { Button } from 'react-bootstrap';
import DisplayPage from './pages/DisplayPage';

class SearchResultsDisplay extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentRecipeID: props.currentRecipeID,
      recipes: props.recipes,
    };
  }

  componentDidUpdate(prevProps) {
    if (prevProps.currentRecipeID !== this.props.currentRecipeID) {
      this.setState({ currentRecipeID: this.props.currentRecipeID });
    }
  }

  handlePrevClick = () => {
    this.setState((prevState) => ({
      currentRecipeID: prevState.currentRecipeID - 1,
    }));
  };

  handleNextClick = () => {
    this.setState((prevState) => ({
      currentRecipeID: prevState.currentRecipeID + 1,
    }));
  };

  render() {
    const { currentRecipeID, recipes } = this.state;

    return (
      <div key={currentRecipeID}>
        <Button onClick={this.handlePrevClick}>{'<'}</Button>
        <DisplayPage data={recipes[currentRecipeID]} />
        <Button onClick={this.handleNextClick}>{'>'}</Button>
      </div>
    );
  }
}

export default SearchResultsDisplay;
