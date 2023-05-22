import React, { useState } from 'react';
import { Row, Col, Button, Form } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

import RecipeDisplay from '../pages/RecipeDisplayPage';
import DuplicateRecordAlert from '../pages/DuplicateRecordAlert';
import RecipeForm from './RecipeForm';
import IngredientForm from './IngredientForm';
import StepsForm from './StepsForm';
import TagsForm from './TagsForm';
import '../../styles/MultiStepForm.css';

const MultiStepForm = ({ renderRecordResponse }) => {
  // DATA MEMBERS
  const[showDuplicateAlert, setShowDuplicateAlert] = useState(false);
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({ tags: [] });
  let toAddMoreRecipes = false;
  const navigate = useNavigate();

  // FORM BUTTONS

  const handleNextStep = (data) => {
    setFormData(formData);
    setStep((prevStep) => prevStep + 1);
  };

  const handlePrevStep = () => {
    setStep((prevStep) => prevStep - 1);
  };

  const handleCheckboxChange = (e) => {
    toAddMoreRecipes = e.target.checked;
  }

  const handleMasterSubmit = () => {
    console.log('Submitted Data:', formData);
  
    fetch('http://localhost:8080/digitalkitchen/recipes/createRecipe', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (response.status === 201) {
          console.log('record created');
          return response.json(); 
        } else if (response.status === 409) {
          console.log('duplicate record!');
          setShowDuplicateAlert(true);
        }  
      })
      .then((data) => {
        if (toAddMoreRecipes) {
          console.log('Navigating to create-recipe page');
          window.location.reload();
        } else {
    
        }
      })
      .catch((error) => {
        console.error('Error:', error);
        // Display a regular alert with the error message
        const result = confirm(error);
        if (result) {
          console.log('OK button clicked');
          // Refresh the page
          window.location.reload();
        } else {
          console.log('View button clicked');
        }
      });
  };

  // DUPLICATE RECORD ALERT


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
    <Row>
      {renderFormStep()}
      <Row md={12}>
        {step > 1 && (
          <Button variant="primary" className="mr-2" onClick={handlePrevStep}>
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
            onClick={() => handleCheckboxChange(event)}
          />
        </Form.Group>
        <Button variant="primary" size="lg" onClick={handleMasterSubmit}>
          Submit
        </Button>
      </Row>
      {showDuplicateAlert && (
        <DuplicateRecordAlert renderRecordResponse={renderRecordResponse} />
      )}
    </Row>
  );
};

export default MultiStepForm;
