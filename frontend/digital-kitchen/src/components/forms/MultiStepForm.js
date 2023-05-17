import React, { useState } from 'react';
import RecipeForm from './RecipeForm';
import IngredientForm from './IngredientForm';
import StepsForm from './StepsForm';
import TagsForm from './TagsForm';

const MultiStepForm = () => {
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({ tags: [] });

  const handleNextStep = (data) => {
    setFormData(formData);
    setStep((prevStep) => prevStep + 1);
  };
  

  const handlePrevStep = () => {
    setStep((prevStep) => prevStep - 1);
  };

  const handleMasterSubmit = () => {
    console.log('Submitted Data:', formData);
  
    // Make an HTTP request to send the form data to the backend
    fetch('http://localhost:8080/digitalkitchen/recipes/createRecipe', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Response from Backend:', data);
        // Handle the response from the backend if needed
      })
      .catch((error) => {
        console.error('Error:', error);
        // Handle errors if any
      });
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
    <div>
      {renderFormStep()}
      <div>
        {step > 1 && (
          <button onClick={handlePrevStep}>Previous</button>
        )}
        {step < 4 ? (
          <button onClick={() => handleNextStep({})}>Next</button>
        ) : (
          <button onClick={handleMasterSubmit}>Submit</button>
        )}
      </div>
    </div>
  );
};

export default MultiStepForm;
