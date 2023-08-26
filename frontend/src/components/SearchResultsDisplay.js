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
		console.log(this.state.currentRecipeID)
		if (this.state.currentRecipeID > 0) {
			this.setState((prevState) => ({
				currentRecipeID: prevState.currentRecipeID - 1,
			}));
		}
	};

	handleNextClick = () => {
		if (this.state.currentRecipeID < this.state.recipes.length - 1) {
			this.setState((prevState) => ({
				currentRecipeID: prevState.currentRecipeID + 1,
			}));
		}
	};

	render() {
		const { currentRecipeID, recipes } = this.state;
		return (
			<div key={currentRecipeID}>
				<Button onClick={this.handlePrevClick}>{'<'}</Button>
				<Button onClick={this.handleNextClick}>{'>'}</Button>
				<DisplayPage data={recipes[currentRecipeID]} />
			</div>
		);
	}
}

export default SearchResultsDisplay;
