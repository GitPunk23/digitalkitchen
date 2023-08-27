import React, { useState } from 'react';
import { Row, Col, Button, Form } from "react-bootstrap";

import DuplicateRecordAlert from '../pages/DuplicateRecordAlert';
import RecipeForm from './RecipeForm';
import IngredientForm from './IngredientForm';
import StepsForm from './StepsForm';
import TagsForm from './TagsForm';
import '../../styles/MultiStepForm.css';


const MultiStepForm = ({ renderRecordResponse }) => {
	const[showDuplicateAlert, setShowDuplicateAlert] = useState(false);
	const [step, setStep] = useState(1);
	const [formData, setFormData] = useState({ 
	ingredients : [],
	steps : [], 
	tags: [] });
	const [toAddMoreRecipes, setMoreRecipes] = useState(false);
	const [record, setRecord] = useState("");
	const [isFormValid, setIsFormValid] = useState(false);

	const handleNextStep = (data) => {
		setFormData(formData);
		setStep((prevStep) => prevStep + 1);
		validateForm();
	};

	const handlePrevStep = () => {
		setStep((prevStep) => prevStep - 1);
		validateForm();
	};
	const handleCheckboxChange = (e) => { setMoreRecipes(e.target.checked); }
	const closeAlert = () => { setShowDuplicateAlert(false); }
	const validateForm = () => {
		const isValid =
			formData.recipe.name !== null  && 
			formData.recipe.name !== undefined && 
			formData.recipe.name !== '' &&
			formData.recipe.author !== null && 
			formData.recipe.author !== undefined && 
			formData.recipe.author !== '' &&
			formData.recipe.category !== null && 
			formData.recipe.category !== undefined && 
			formData.recipe.category !== '' &&
			formData.ingredients !== undefined &&
			formData.ingredients.length > 0 &&
			formData.steps !== undefined &&
			formData.steps.length > 0;
		setIsFormValid(isValid);
	};

	const handleMasterSubmit = async () => {
		try {
			const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/createRecipe`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			});
			console.log(response);
			const json = await response.json();
			setRecord(json);
			if (response.status === 201) {
				if (toAddMoreRecipes) {
					window.location.reload(true);
				} else {
					renderRecordResponse(json);
				}
			} else if (response.status === 409) {
				setShowDuplicateAlert(true);
			}
		} catch (error) {
			const result = confirm(error);
			console.error('Error:', error); 
		}
	};

	const submitDuplicate = async () => {
		try {
			const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/createRecipe?bypass=true`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			});
			const json = await response.json();
			setRecord(json);
			if (response.status === 201) {
				console.log('record created: ',json);
				if (toAddMoreRecipes) {
					window.location.reload(true);
				} else {
					renderRecordResponse(json);
				}
			} 
		} catch (error) {
			console.error('Error:', error);
			const result = confirm(error); 
		}
	};

	const renderFormStep = () => {
		switch (step) {
			case 1:
			return (
				<RecipeForm
				onNextStep={handleNextStep}
				formData={formData}
				setFormData={setFormData}
				/>
			);
			case 2:
			return (
				<IngredientForm
				onNextStep={handleNextStep}
				onPrevStep={handlePrevStep}
				formData={formData}
				setFormData={setFormData}
				/>
			);
			case 3:
			return (
				<StepsForm
				onNextStep={handleNextStep}
				onPrevStep={handlePrevStep}
				formData={formData}
				setFormData={setFormData}
				/>
			);
			case 4:
			return (
				<TagsForm
				onPrevStep={handlePrevStep}
				formData={formData}
				setFormData={setFormData}
				/>
			);
			default:
			return null;
		}
	};

	return (
		<Row> { renderFormStep() }
			<Row md={12}>
				{step > 1 && (
					<Button 
					variant="primary" 
					className="mr-2" 
					onClick={handlePrevStep}>
					Previous
					</Button>
				)}
				{step < 4 && (
					<Button variant="primary" onClick={() => handleNextStep({})}>
					Next
					</Button>
				)}
			</Row>
			<Row md={12} className="mt-3">
				<Form.Group className="mb-0">
					<Form.Check
					type="checkbox"
					id="moreRecipesCheckbox"
					label="I have more recipes"
					onClick={(event) => handleCheckboxChange(event)}
					/>
				</Form.Group>
				<Button 
					variant="primary" 
					size="lg" 
					disabled={!isFormValid}
					onClick={handleMasterSubmit}>
						Submit
				</Button>
			</Row>
			{showDuplicateAlert && (<DuplicateRecordAlert 
				renderRecordResponse={renderRecordResponse} 
				submit={submitDuplicate}
				close={closeAlert}
				record={record}/> )}
				
		</Row>
	);
};

export default MultiStepForm;
