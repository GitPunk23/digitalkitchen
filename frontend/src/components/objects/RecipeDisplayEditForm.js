import React, { useState, useEffect } from 'react';
import { Form, Button, ListGroup, Table, } from 'react-bootstrap';
import AutosuggestTextBox from '../forms/form-components/AutosuggestTextBox';
import FetchManager from '../util/FetchManager';

const RecipeDisplayEditForm = ({ recipeForm, handleSubmit, handleReset }) => {
    const [editedFormData, setEditedFormData] = useState(recipeForm)
    const [ingredientsSuggestionsList, setIngredientsSuggestionsList] = useState( [] );
	const [measurementsList, setMeasurementsList] = useState( [] );

    useEffect(() => {
        FetchManager.fetchFormIngredientsList()
			.then((data) => {
				setIngredientsSuggestionsList(data);
			})
			.catch((error) => {
				console.log(error);
			})

		FetchManager.fetchFormMeasurementsList()
			.then((data) => {
                const measurements = Object.values(data).map((item) => item.measurement);
				setMeasurementsList(measurements);
			})
			.catch((error) => {
				console.log(error);
			})
    }, []);

    const handleChange = (e) => {
		const { name, value } = e.target;
		console.log(e.target);
		setEditedFormData(prevData => ({
			...prevData,
			[name]: value,
		}));
  	};

    const handleAddIngredient = () => {
		const lastIndex = editedFormData.ingredients.length - 1;
		const lastIngredient = editedFormData.ingredients[lastIndex];
		if( lastIngredient.ingredient == '' || lastIngredient.measurement == '' || lastIngredient.quantity == 0 ) {
			console.error("Enter missing fields in ingredient", lastIndex);
			return;
		}
		setEditedFormData(prevData => ({
			...prevData,
			ingredients: [
				...prevData.ingredients,
				{ ingredient: '', measurement: '', quantity: 0, notes: '' }
			]
		}));
	};	

    const handleIngredientChange = (index, property, value) => {
        const updatedIngredients = [...editedFormData.ingredients];
        updatedIngredients[index][property] = value;
        setEditedFormData(prevData => ({
			...prevData,
			ingredients: updatedIngredients,
		}));
      };

	const handleDeleteIngredient = (index) => {
		if (editedFormData.ingredients.length < 2) {
			console.error("Recipes must have at least one ingredient");
			return;
		}
		const updatedIngredients = [...editedFormData.ingredients];
		updatedIngredients.splice(index, 1);
		
		setEditedFormData(prevFormData => ({
			...prevFormData,
			ingredients: updatedIngredients,
		}));
	};

	const handleAddStep = () => {

	};

	const handleMoveStep = () => {

	};

	const handleDeleteStep = () => {

	};

	const handleAddTag = () => {

	};

	const handleDeleteTag = () => {

	};

	const submitForm = () => {
		handleSubmit(editedFormData);
	}

    return (
        <Form>
				<Form.Group controlId="recipeName">
					<Form.Label>Name</Form.Label>
					<Form.Control
						type="text"
						name="name"
						value={editedFormData.name}
						onChange={handleChange}
					/>
				</Form.Group>
				<Form.Group controlId="recipeAuthor">
					<Form.Label>Author</Form.Label>
					<Form.Control
						type="text"
						name="author"
						value={editedFormData.author}
						onChange={handleChange}
					/>
				</Form.Group>
				<Form.Group controlId="recipeDescription">
					<Form.Label>Description</Form.Label>
					<Form.Control
						as="textarea"
						name="description"
						value={editedFormData.description}
						onChange={handleChange}
					/>
				</Form.Group>
				<Form.Group controlId="recipeServings">
					<Form.Label>Servings</Form.Label>
					<Form.Control
						type="number"
						name="servings"
						value={editedFormData.servings}
						onChange={handleChange}
					/>
				</Form.Group>
				<Form.Group controlId="recipeCaloriesPerServing">
					<Form.Label>Calories per Serving</Form.Label>
					<Form.Control
						type="number"
						name="caloriesPerServing"
						value={editedFormData.caloriesPerServing}
						onChange={handleChange}
					/>
				</Form.Group>
				<Form.Group controlId="recipeNotes">
					<Form.Label>Notes</Form.Label>
					<Form.Control
						as="textarea"
						name="notes"
						value={editedFormData.notes}
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
							{editedFormData.ingredients.map((ingredient, index) => (
							<tr key={index}>
								<td>
									<AutosuggestTextBox
										data={ingredientsSuggestionsList} 
										value={ingredient.ingredient}
										setValue={(value) => handleIngredientChange(index,'ingredient', value)}
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
						{editedFormData.steps.map((step, index) => (
						<ListGroup.Item key={index}>
							{step.stepNumber}. 
							<Form.Control
							type="text"
							name="caloriesPerServing"
							value={step.description}
							onChange={handleChange}/>
						</ListGroup.Item>
						))}
					</ListGroup>
				</Form.Group>
				<Form.Group controlId="recipeTags">
					<Form.Label><strong>Tags</strong></Form.Label>
					<ListGroup>
						{editedFormData.tags.map((tag, index) => (
							<ListGroup.Item key={index}>
								{tag}
								<Form.Control
								type="text"
								name="tags"
								value={tag}
								onChange={handleChange}/>
							</ListGroup.Item>
						))}
					</ListGroup>
				</Form.Group>
				<Button variant="primary" onClick={submitForm}>
					Save
				</Button>
				<Button variant="primary" onClick={handleReset}>
					Cancel
				</Button>
			</Form>
    );
}

export default RecipeDisplayEditForm