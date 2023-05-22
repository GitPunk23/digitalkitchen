import React from 'react';
import { useNavigate } from 'react-router-dom';
import MultiStepForm from '../forms/MultiStepForm';
import RecipeDisplay from './RecipeDisplayPage';
import Recipe from '../Recipe';
import DisplayRecordPage from './DisplayRecordPage';
//import '../styles/CreateRecipePage.css'

function CreateRecipePage() {
  const navigate = useNavigate();
  const goHome = () => {
    navigate('/');
  };

  const renderRecordResponse = (record) => {
    console.log(record);
  }

  return (
    <div>
      <button onClick={goHome} style={{ position: 'absolute', top: 0, left: 0 }}>
        Home
      </button>
      <h1>Create A Recipe</h1>
      <MultiStepForm renderRecordResponse={(renderRecordResponse)}/>
    </div>
  );
}

export default CreateRecipePage;
