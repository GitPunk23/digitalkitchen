import React, { useState } from 'react';
import { Form, Button, ListGroup, Table } from 'react-bootstrap';
import FetchManager from '../util/FetchManager';
import RecipeDisplayEditForm from './RecipeDisplayEditForm';

function RecipeDisplay(props) {
	const [isEditing, setIsEditing] = useState(false);
	const [formData, setFormData] = useState({ ...props.formData });

	const { updateRecipe } = props;
	
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
				body: JSON.stringify(formData.id),
		});

		if (response.status === 200) {
			window.location.reload(true);
		}
		} catch (error) {
			console.log(error);
		}
	};

	const handleSubmit = async (editedFormData) => {
		setIsEditing(false);
		//updateRecipe(editedFormData);
		try {
			const updatedRecipeData = await FetchManager.updateRecipeData(editedFormData);
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
			{isEditing ? (
				<RecipeDisplayEditForm
					recipeForm={formData}
					handleSubmit={handleSubmit}
					handleReset={handleReset}/>
			) : (
			<div>
				<p>
					<strong>Name:</strong> {formData.name}
				</p>
				<p>
					<strong>Author:</strong> {formData.author}
				</p>
				<p>
					<strong>Category:</strong> {formData.category}
				</p>
				<p>
					<strong>Description:</strong> {formData.description}
				</p>
				<p>
					<strong>Servings:</strong> {formData.servings}
				</p>
				<p>
					<strong>Calories per Serving:</strong> {formData.caloriesPerServing}
				</p>
				<p>
					<strong>Notes:</strong> {formData.notes}
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
						{(formData.ingredients || []).map((ingredient, index) => (
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
					{(formData.steps || []).map((step, index) => (
					<li key={index}>
						{`${step.description}`}
					</li>
					))}
				</ol>
				</div>

				<div>
					<strong>Tags:</strong>
					<ul>
						{(formData.tags || []).map((tag, index) => (
						<li key={index}>{tag}</li>
						))}
					</ul>
				</div>
			</div>
			)}
			{!isEditing && (
				<Button variant="secondary" onClick={handleEdit}>
					Edit
				</Button>
			)}
			<Button variant="secondary" onClick={handleDelete}>
				Delete Recipe
			</Button>
		</div>
	);
}


export default RecipeDisplay;
