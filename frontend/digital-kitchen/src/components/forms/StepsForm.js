import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const StepsForm = ({ formData, setFormData }) => {
  const [description, setDescription] = useState('');
  const [editingIndex, setEditingIndex] = useState(-1);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (editingIndex !== -1) {
      // If editing an existing step
      setFormData((prevFormData) => {
        const updatedSteps = [...prevFormData.steps];
        updatedSteps[editingIndex].description = description;
        return {
          ...prevFormData,
          steps: updatedSteps,
        };
      });
      setEditingIndex(-1);
    } else {
      // If adding a new step
      const newStep = {
        description,
      };
      setFormData((prevFormData) => ({
        ...prevFormData,
        steps: [...(prevFormData?.steps || []), newStep],
      }));
    }
    setDescription('');
  };

  const handleEditStep = (index) => {
    const step = formData.steps[index];
    setDescription(step.description);
    setEditingIndex(index);
  };

  const handleDeleteStep = (index) => {
    setFormData((prevFormData) => {
      const updatedSteps = [...prevFormData.steps];
      updatedSteps.splice(index, 1);
      return {
        ...prevFormData,
        steps: updatedSteps,
      };
    });
  };

  return (
    <div>
      <h2>Add Steps</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formStepDescription">
          <Form.Label>Step</Form.Label>
          <Form.Control
            as="textarea"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </Form.Group>
        <Button type="submit">
          {editingIndex !== -1 ? 'Update Step' : 'Add Step'}
        </Button>
      </Form>
      <div>
        <h3>Steps:</h3>
        {formData && formData.steps && formData.steps.length > 0 ? (
          <ol>
            {formData.steps.map((step, index) => (
              <li key={index}>
                {step.description}
                <Button variant="primary" size="sm" onClick={() => handleEditStep(index)}>
                  Edit
                </Button>
                <Button variant="danger" size="sm" onClick={() => handleDeleteStep(index)}>
                  Delete
                </Button>
              </li>
            ))}
          </ol>
        ) : (
          <p>No steps added yet.</p>
        )}
      </div>
    </div>
  );
};

export default StepsForm;
