import React, { useState } from "react";

function StepsForm({ formData, setFormData, onPrevStep }) {
  const [description, setDescription] = useState("");
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
    setDescription("");
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
      <form onSubmit={handleSubmit}>
        <table>
          <thead>
            <tr>
              <th>Step</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <textarea
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                  required
                />
              </td>
            </tr>
            <tr>
              <td>
                <button type="submit">
                  {editingIndex !== -1 ? "Update Step" : "Add Step"}
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div>
        <h3>Steps:</h3>
        {formData && formData.steps && formData.steps.length > 0 ? (
          <ol>
            {formData.steps.map((step, index) => (
              <li key={index}>
                {step.description}
                <button onClick={() => handleEditStep(index)}>Edit</button>
                <button onClick={() => handleDeleteStep(index)}>Delete</button>
              </li>
            ))}
          </ol>
        ) : (
          <p>No steps added yet.</p>
        )}
      </div>
    </div>
  );
}

export default StepsForm;
