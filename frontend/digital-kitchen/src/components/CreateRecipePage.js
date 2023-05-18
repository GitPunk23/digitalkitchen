import React from 'react';
import { useNavigate } from 'react-router-dom';
import MultiStepForm from './forms/MultiStepForm';

function CreateRecipePage() {
  const navigate = useNavigate();

  const goHome = () => {
    navigate('/');
  };

  return (
    <div>
      <button onClick={goHome} style={{ position: 'absolute', top: 0, left: 0 }}>
        Home
      </button>
      <h2>Create Recipe</h2>
      <MultiStepForm />
    </div>
  );
}

export default CreateRecipePage;
