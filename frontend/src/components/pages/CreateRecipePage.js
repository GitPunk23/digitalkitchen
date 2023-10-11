import React from 'react';
import { useNavigate } from 'react-router-dom';
import Recipe from '../objects/Recipe';

function CreateRecipePage() {
	
  	const navigate = useNavigate();
  	const goHome = () => { navigate('/'); };

	const handleSubmit = (response) => {

	};

	return (
		<div>
			<div>
				<h1>Create A Recipe</h1>
				<Recipe 
					deleteResponse={() => handleSubmit(response)}
					editFlag={true}/>
			</div>
		</div>
	);  
}

export default CreateRecipePage;
