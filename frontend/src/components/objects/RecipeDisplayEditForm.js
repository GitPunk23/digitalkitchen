import React, { useState, useEffect } from 'react';
import { Form, Button, ListGroup, Table, } from 'react-bootstrap';
import AutosuggestTextBox from '../forms/AutosuggestTextBox';
import { useMeasurementsList } from '../../context/MeasurementsContext';
import { useCategoriesList } from '../../context/CategoriesContext';
import { useIngredientsList } from '../../context/IngredientsContext';
import { useTagsList } from '../../context/TagsContext';

const RecipeDisplayEditForm = ({ recipeData, handleSubmit, handleReset }) => {
    const [editedRecipeData, setEditedRecipeData] = useState(recipeData)
	const { measurementsList } = useMeasurementsList();
	const { categoriesList } = useCategoriesList();
	const { ingredientsList } = useIngredientsList();
	const { tagsList } = useTagsList();	 
	const [ creatingRecipe, setCreatingRecipe ] = useState(false); 

	const validateForm = (editedRecipeData) => {
		const isValid =
		editedRecipeData.name !== null  && 
		editedRecipeData.name !== undefined && 
		editedRecipeData.name !== '' &&
		editedRecipeData.author !== null && 
		editedRecipeData.author !== undefined && 
		editedRecipeData.author !== '' &&
		editedRecipeData.category !== null && 
		editedRecipeData.category !== undefined && 
		editedRecipeData.category !== '' &&
		editedRecipeData.ingredients !== undefined &&
		editedRecipeData.ingredients.length > 0 &&
		editedRecipeData.steps !== undefined &&
		editedRecipeData.steps.length > 0;
		return isValid;
	};

	const prepareSubmit = (editedFormData) => {
		if (validateForm(editedFormData)) {
			handleSubmit(editedFormData)
		} else {
			console.error("Form is invalid!");
		};
	}

    const handleChange = (e) => {
		const { name, value } = e.target;
		setEditedRecipeData(prevData => ({
			...prevData,
			[name]: value,
		}));
  	};

    const handleAddIngredient = () => {
		const lastIndex = editedRecipeData.ingredients.length - 1;
		const lastIngredient = editedRecipeData.ingredients[lastIndex];
		if( lastIngredient.ingredient == '' || lastIngredient.measurement == '' || lastIngredient.quantity == 0 ) {
			console.error("Enter missing fields in ingredient", lastIndex);
			return;
		}
		setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			ingredients: [
				...prevRecipeData.ingredients,
				{ ingredient: '', measurement: '', quantity: 0, notes: '' }
			]
		}));
	};	
	
    const handleIngredientChange = (index, property, value) => {
        const editedIngredients = [...editedRecipeData.ingredients];
        editedIngredients[index][property] = value;

        setEditedRecipeData(prevData => ({
			...prevData,
			ingredients: editedIngredients,
		}));
    };

	const handleDeleteIngredient = (index) => {
		if (editedRecipeData.ingredients.length < 2) {
			console.error("Recipes must have at least one ingredient");
			return;
		}
		const editedIngredients = [...editedRecipeData.ingredients];
		editedIngredients.splice(index, 1);
		
		setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			ingredients: editedIngredients,
		}));
	};

	const handleAddStep = () => {
		const lastIndex = editedRecipeData.steps.length - 1;
		const lastStep = editedRecipeData.steps[lastIndex];
		if( lastStep.description == '') {
			console.error("Enter missing fields in step", lastIndex);
			return;
		}
		setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			steps: [
				...prevRecipeData.steps,
				{ stepNumber: editedRecipeData.steps.length + 1, description: ''}
			]
		}));
	};

	const handleStepChange = (index, value) => {
        const editedSteps = [...editedRecipeData.steps];
        editedSteps[index].description = value;

        setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			steps: editedSteps,
		}));
    };

	const handleDeleteStep = (index) => {
		if (editedRecipeData.steps.length < 2) {
			console.error("Recipes must have at least one step");
			return;
		}
		
		const editedSteps = [...editedRecipeData.steps];
		editedSteps.splice(index, 1);

		for (let i = index; i < editedSteps.length; i++) {
			editedSteps[i].stepNumber = i + 1; 
		}
		
		setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			steps: editedSteps,
		}));
	};

	const handleAddTag = (tag) => {
		if (tag === '') {
		  	return;
		}
		setEditedRecipeData((prevRecipeData) => ({
		  	...prevRecipeData,
		  	tags: [...prevRecipeData.tags, tag],
		}));
	};

	const handleDeleteTag = (index) => {
		const editedTags = [...editedRecipeData.tags];
		editedTags.splice(index, 1);

		setEditedRecipeData(prevRecipeData => ({
			...prevRecipeData,
			tags: editedTags,
		}));
	};

    return (
        <Form>
			<Form.Group controlId="recipeName">
				<Form.Label>Name</Form.Label>
				<Form.Control
					type="text"
					name="name"
					value={editedRecipeData.name}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeAuthor">
				<Form.Label>Author</Form.Label>
				<Form.Control
					type="text"
					name="author"
					value={editedRecipeData.author}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeCategory">
				<Form.Label>Category</Form.Label>
				<Form.Control
					as="select" 
					name="category"
					value={editedRecipeData.category}
					onChange={handleChange}
				>
					<option value="">Select a category</option> {/* Optionally, add a default option */}
					{categoriesList.map((option, index) => (
						<option key={index} value={option}>
							{option}
						</option>
					))}
				</Form.Control>
			</Form.Group>
			<Form.Group controlId="recipeDescription">
				<Form.Label>Description</Form.Label>
				<Form.Control
					as="textarea"
					name="description"
					value={editedRecipeData.description}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeServings">
				<Form.Label>Servings</Form.Label>
				<Form.Control
					type="number"
					name="servings"
					value={editedRecipeData.servings}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeCaloriesPerServing">
				<Form.Label>Calories per Serving</Form.Label>
				<Form.Control
					type="number"
					name="caloriesPerServing"
					value={editedRecipeData.caloriesPerServing}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeNotes">
				<Form.Label>Notes</Form.Label>
				<Form.Control
					as="textarea"
					name="notes"
					value={editedRecipeData.notes}
					onChange={handleChange}
				/>
			</Form.Group>
			<Form.Group controlId="recipeIngredients">
				<Form.Label><strong>Ingredients</strong></Form.Label>
				<Table striped bordered>
					<thead>
						<tr>
							<th>Ingredient</th>
							<th>Measurement</th>
							<th>Quantity</th>
							<th>Notes</th>
						</tr>
					</thead>
					<tbody>
						{editedRecipeData.ingredients.map((ingredient, index) => (
						<tr key={index}>
							<td>
								<AutosuggestTextBox
									data={ingredientsList} 
									value={ingredient.ingredient}
									setValue={(value) => handleIngredientChange(index,'ingredient', value)}
									onValueChange={(value) => handleIngredientChange(index,'ingredient', value)}
									returnSuggestion={(value) => handleIngredientChange(index,'ingredient', value)}
								>
								</AutosuggestTextBox>
							</td>
							<td>
								<select
									name="measurement"
									value={ingredient.measurement || ''}
									onChange={(event) => handleIngredientChange(index, 'measurement', event.target.value)}
								>
									<option value="">Select Measurement</option>
										{measurementsList.map((measurement, measurementIndex) => (
											<option key={measurementIndex} value={measurement}>
												{measurement}
											</option>
										))}
								</select>
							</td>
							<td>
								<Form.Control
									type="number"
									name="quantity"
									value={ingredient.quantity}
									onChange={(event) => handleIngredientChange(index, 'quantity', parseFloat(event.target.value))}
								/>
							</td>
							<td>
								<Form.Control
									type="text"
									name="notes"
									value={ingredient.notes}
									onChange={(event) => handleIngredientChange(index, 'notes', event.target.value)}
								/>
							</td>
							<td>
								<Button variant="danger" onClick={() => handleDeleteIngredient(index)}> 	
									X
								</Button>
							</td>
						</tr>
						))}
						<tr>
							<td colSpan="5">
								<Button onClick={handleAddIngredient}>
									+
								</Button>
							</td>
						</tr>
					</tbody>
				</Table>
			</Form.Group>
			<Form.Group controlId="recipeSteps">
				<Form.Label><strong>Steps</strong></Form.Label>
				<ListGroup>
					{editedRecipeData.steps.map((step, index) => (
					<ListGroup.Item key={index}>
						{step.stepNumber}. 
						<Form.Control
							type="text"
							name={`steps[${index}].description`}
							value={step.description}
							onChange={(event) => handleStepChange(index, event.target.value)}/>
						<Button variant="danger" onClick={() => handleDeleteStep(index)}>
							X
						</Button>
					</ListGroup.Item>
					))}
				</ListGroup>
				<Button variant="primary" onClick={handleAddStep}>+</Button>
			</Form.Group>
			<Form.Group controlId="recipeTags">
				<Form.Label><strong>Tags</strong></Form.Label>
				<div className="tag-container">
					<AutosuggestTextBox
						data={tagsList} 
						setValue={(value) => handleAddTag(value)}
						returnSuggestion={(value) => handleAddTag(value)}
						placeholder={"Enter tag name..."}
						clearInputOnSubmit={true}
					></AutosuggestTextBox>
					{editedRecipeData.tags.map((tag, index) => (
						<div key={index} className="tag">
							<span>{tag}</span>
							<Button
								variant="danger"
								size="sm"
								onClick={() => handleDeleteTag(index)}
							>
								X
							</Button>
						</div>
					))}
				</div>
			</Form.Group>
			<Button variant="primary" onClick={() => prepareSubmit(editedRecipeData)}>
				Save
			</Button>
			<Button variant="primary" onClick={handleReset}>
				Cancel
			</Button>
		</Form>
    );
}

export default RecipeDisplayEditForm