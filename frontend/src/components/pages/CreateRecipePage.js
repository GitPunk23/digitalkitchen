import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MultiStepForm from '../forms/MultiStepForm';
import RecipeDisplay from './DisplayPage';
import Recipe from '../objects/Recipe';
import DisplayPage from './DisplayPage';
//import '../styles/CreateRecipePage.css'

function CreateRecipePage() {
	
  	const navigate = useNavigate();
  	const goHome = () => { navigate('/'); };
  	const [recordResponse, setRecordResponse] = useState(null);
  	const renderRecordResponse = (record) => { setRecordResponse(record); };

	return (
		<div>
			<div>
				<h1>Create A Recipe</h1>
				{recordResponse ? null : <MultiStepForm renderRecordResponse={renderRecordResponse} />}
			</div>
			{recordResponse && <DisplayPage data={recordResponse}/>}
		</div>
	);  
}

export default CreateRecipePage;
