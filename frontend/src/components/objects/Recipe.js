import React, { useState, useEffect } from 'react';
import RecipeDisplayEditForm from './RecipeDisplayEditForm';
import RecipeDisplay from './RecipeDisplay';
import FetchManager from '../util/FetchManager';
import DuplicateRecordAlert from '../pages/DuplicateRecordAlert';
import { Button } from 'react-bootstrap';

function Recipe({ recipeData, submitResponse, deleteResponse, editFlag }) {
	const [isEditing, setIsEditing] = useState(editFlag);
	const[showDuplicateAlert, setShowDuplicateAlert] = useState(false);
	const [duplicateRecipeData, setDuplicateRecipeData] = useState(null);
	const [showDuplicateRecipe, setShowDuplicateRecipe] = useState(false);

	const emptyRecipeData = {
		name: '',
		author: '',
		category: '',
		description: '',
		servings: '',
		caloriesPerServing: '',
		notes: '',
		ingredients: [
			{
				ingredient: '',
				measurement: '',
				quantity: 0, 
				notes: '',
			},
		],
		steps: [
			{
				stepNumber: 1, 
				description: '',
			},
		],
		tags: [],
	};

	const handleReset = () => {
		setIsEditing(false);
	}

	const handleEdit = () => {
		setIsEditing(true);
	}

	const viewRecipe = () => {
		console.log(duplicateRecipeData);
		handleEdit();
		setShowDuplicateAlert(false);
		setShowDuplicateRecipe(true);
	}

	const handleSubmit = async (editedRecipeData) => {
		try {
			const response = await FetchManager.postRecipe(editedRecipeData);
			console.log(response);
			
			if (response.status === 201) {
				if (submitResponse) { submitResponse(response) }
				window.location.reload(true);
	
			} else if (response.status === 409) {
				if (submitResponse) { submitResponse(response) };
				const data = await response.json();
				setDuplicateRecipeData(data)
				setShowDuplicateAlert(true);
			} else {
				console.error('Error:', error);
			}
		} catch (error) {
			console.error('Error:', error); 
		}
	}

	const submitDuplicate = async () => {
		try {
			const response = await FetchManager.postRecipeDuplicate(recipeData);
			console.log(response)

			if (response.status === 201) {
				if (toAddMoreRecipes) {
					window.location.reload(true);
				} else {
					console.log(json);
				}
			} 
		} catch (error) {
			console.error('Error:', error);
			const result = confirm(error); 
		}
	};

	return (
		<div>
			{isEditing ? (
				<RecipeDisplayEditForm
				recipeData={recipeData || emptyRecipeData}
				handleSubmit={handleSubmit}
				handleReset={handleReset}
				/>
			) : showDuplicateRecipe && duplicateRecipeData ? (
				// Maybe switch this to a modal
				<RecipeDisplay
				recipeData={duplicateRecipeData}
				handleEdit={handleEdit}
				/>
			) : (
					<>
						<RecipeDisplay
							recipeData={recipeData || emptyRecipeData}
							handleEdit={handleEdit} />
						<Button onClick={handleEdit}>Edit</Button>
					</>
				
			)}
		  	{showDuplicateAlert && (
			<DuplicateRecordAlert  
				viewRecipe={() => viewRecipe()}
				submitDuplicate={() => submitDuplicate()}
				closeAlert={() => setShowDuplicateAlert(false)}
			/>
		  	)}
		</div>
	);  
}

export default Recipe;
