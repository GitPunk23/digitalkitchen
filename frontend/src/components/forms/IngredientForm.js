import React, { useState, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import AutosuggestTextBox from './form-components/AutosuggestTextBox';
import FetchManager from '../util/FetchManager';

const IngredientForm = ({ formData, setFormData }) => {
	const [ingredients, setIngredients] = useState(formData?.ingredients || []);
	const [ingredient, setIngredient] = useState('');
	const [quantity, setQuantity] = useState('');
	const [measurement, setMeasurement] = useState('');
	const [notes, setNotes] = useState('');
	const [measurements, setMeasurements] = useState([]);
	const [ingredientsSuggestionsList, setIngredientsSuggestionsList] = useState([]);
	const [ingredientSuggestionsLoaded, setIngredientSuggestionsLoaded] = useState(false);
	const [measurementsLoaded, setmeasurementsLoaded] = useState(false);
	const [backendDataLoaded, setBackendDataLoaded] = useState(false);	

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
				setMeasurements(data)
			})
			.catch((error) => {
				console.log(error);
			})
	}, []);

	useEffect(() => {
		setIngredientSuggestionsLoaded(ingredientsSuggestionsList.length > 0);
		setmeasurementsLoaded(measurements.length > 0);
		setBackendDataLoaded(ingredientSuggestionsLoaded && 
							measurementsLoaded);
	});

	useEffect(() => {
		setFormData((prevFormData) => ({
			...prevFormData,
			ingredients,
		}));
	}, [ingredients, setFormData]);
		
	const returnSuggestion = (suggestion) => {
		setIngredient(suggestion);
	}

	const handleSubmit = (e) => {
		e.preventDefault();
		const newIngredient = {
			ingredient,
			quantity,
			measurement,
			notes,
		};
		setIngredients([...ingredients, newIngredient]);
		setIngredient('');
		setQuantity('');
		setMeasurement('');
		setNotes('');
	};

	const handleEditIngredient = (index) => {
		const ingredient = ingredients[index];
		setIngredient(ingredient.ingredient);
		setQuantity(ingredient.quantity);
		setMeasurement(ingredient.measurement);
		setNotes(ingredient.notes);
		setIngredients((prevIngredients) => {
			const updatedIngredients = [...prevIngredients];
			updatedIngredients.splice(index, 1);
			return updatedIngredients;
		});
	};

	const handleDeleteIngredient = (index) => {
		setIngredients((prevIngredients) => {
			const updatedIngredients = [...prevIngredients];
			updatedIngredients.splice(index, 1);
			return updatedIngredients;
		});
	};

	return (
		<div>
			<h2>Add Ingredients</h2>
			<Form onSubmit={handleSubmit}>
				<Table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Quantity</th>
							<th>Measurement</th>
							<th>Notes</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								{ingredientsSuggestionsList.length > 0 &&
									backendDataLoaded &&
									<AutosuggestTextBox 
										data={ingredientsSuggestionsList} 
										value={ingredient}
										setValue={setIngredient}
										returnSuggestion={returnSuggestion}
									/>}
							</td>
							<td>
								<Form.Control
									type="number"
									step="0.01"
									value={quantity}
									onChange={(e) => setQuantity(e.target.value)}
									required
								/>
							</td>
							<td>
								<Form.Select
									value={measurement}
									onChange={(e) => setMeasurement(e.target.value)}
									required
									>
									<option value="">-- Select a measurement --</option>
									{measurements.map((measurement) => (
										<option key={measurement.id} value={measurement.id}>
										{measurement.measurement}
										</option>
									))}
								</Form.Select>
							</td>
							<td>
								<Form.Control
									type="text"
									value={notes}
									onChange={(e) => setNotes(e.target.value)}
								/>
							</td>
							<td>
								<Button type="submit">
								{ingredient ? 'Update Ingredient' : 'Add Ingredient'}
								</Button>
							</td>
						</tr>
					</tbody>
				</Table>
			</Form>
			<div>
				<h3>Ingredients:</h3>
				{ingredients.length > 0 ? (
					<ul> {ingredients.map((ingredient, index) => (
						<li key={index}>
							{ingredient.quantity} {ingredient.measurement} {ingredient.ingredient} - {ingredient.notes}
							<Button variant="primary" size="sm" onClick={() => handleEditIngredient(index)}>
								Edit
							</Button>
							<Button variant="danger" size="sm" onClick={() => handleDeleteIngredient(index)}>
								Delete
							</Button>
						</li>))}
					</ul>
				) : (
					<p>No ingredients added yet.</p>
				)}
			</div>
		</div>
	);
};

export default IngredientForm;
