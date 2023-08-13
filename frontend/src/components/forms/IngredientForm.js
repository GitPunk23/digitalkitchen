import React, { useState, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

const IngredientForm = ({ formData, setFormData }) => {
	const [ingredients, setIngredients] = useState(formData?.ingredients || []);
	const [ingredient, setIngredient] = useState('');
	const [quantity, setQuantity] = useState('');
	const [measurement, setMeasurement] = useState('');
	const [notes, setNotes] = useState('');
	const [measurements, setMeasurements] = useState([]);

	useEffect(() => {
		fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/measurements`)
		.then((response) => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json(); })
		.then((data) => { setMeasurements(data);})
		.catch((error) => { console.error('Fetch error:', error); });
	}, []);
		
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

	useEffect(() => {
		setFormData((prevFormData) => ({
			...prevFormData,
			ingredients,
		}));
	}, [ingredients, setFormData]);

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
								<Form.Control
									type="text"
									value={ingredient}
									onChange={(e) => setIngredient(e.target.value)}
									required
								/>
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
