import React, { useState } from 'react';
import { Form, Button, ListGroup, Table } from 'react-bootstrap';
import FetchManager from '../util/FetchManager';
import RecipeDisplayEditForm from './RecipeDisplayEditForm';
import { useGroceryList } from '../../context/GroceryListContext';

function RecipeDisplay(props) {
	const [isEditing, setIsEditing] = useState(false);
	const [recipeData, setRecipeData] = useState({ ...props.recipeData });
	const { groceryList, setGroceryList } = useGroceryList();

	const send = async () => {
		const recipeList = [];
		recipeList.push(recipeData);
		const newList = await FetchManager.fetchGroceryList(recipeList);
		addToGroceryList(newList);
		console.log(groceryList);
	}

	const addToGroceryList = (item) => {
		setGroceryList(item);
	};

	const handleEdit = () => {
		setIsEditing(true);
	};

	const handleDelete = async (e) => {
		try {
			const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/delete`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(recipeData.id),
		});

		if (response.status === 200) {
			window.location.reload(true);
		}
		} catch (error) {
			console.log(error);
		}
	};

	const handleSubmit = async (editedRecipeData) => {
		setIsEditing(false);
		//updateRecipe(editedFormData);
		try {
			const updatedRecipeData = await FetchManager.updateRecipeData(editedRecipeData);
			if ( updatedRecipeData ) {
				updateRecipe(updatedRecipeData)
			}
			
		} catch (error) {
			console.log(error);
		}
	};

	const handleReset = () => {
		setIsEditing(false);
	};

  	return (
    	<div>
      		<h2>Recipe Details</h2>
			<div>
				<p>
					<strong>Name:</strong> {recipeData.name}
				</p>
				<p>
					<strong>Author:</strong> {recipeData.author}
				</p>
				<p>
					<strong>Category:</strong> {recipeData.category}
				</p>
				<p>
					<strong>Description:</strong> {recipeData.description}
				</p>
				<p>
					<strong>Servings:</strong> {recipeData.servings}
				</p>
				<p>
					<strong>Calories per Serving:</strong> {recipeData.caloriesPerServing}
				</p>
				<p>
					<strong>Notes:</strong> {recipeData.notes}
				</p>

				<div>
					<strong>Ingredients:</strong>
					<Table striped bordered>
						<thead>
							<tr>
								<th>Ingredient</th>
								<th>Quantity</th>
								<th>Measurement</th>
								<th>Notes</th>
							</tr>
						</thead>
						<tbody>
						{(recipeData.ingredients || []).map((ingredient, index) => (
							<tr key={index}>
							<td>{ingredient.ingredient}</td>
							<td>{ingredient.quantity}</td>
							<td>{ingredient.measurement}</td>
							<td>{ingredient.notes}</td>
							</tr>
						))}
						</tbody>
					</Table>
				</div>
				<div>
				<strong>Steps:</strong>
				<ol>
					{(recipeData.steps || []).map((step, index) => (
					<li key={index}>
						{`${step.description}`}
					</li>
					))}
				</ol>
				</div>

				<div>
					<strong>Tags:</strong>
					<ul>
						{(recipeData.tags || []).map((tag, index) => (
						<li key={index}>{tag}</li>
						))}
					</ul>
				</div>
			</div>
			<Button variant="secondary" onClick={handleDelete}>
				Delete Recipe
			</Button>
			<Button onClick={send}>
				Grocery
			</Button>
		</div>
	);
}


export default RecipeDisplay;
