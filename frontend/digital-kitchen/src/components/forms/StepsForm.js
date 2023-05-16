import React, { useState } from "react";

function StepsForm({ formData, setFormData, onPrevStep }) {
    const [description, setDescription] = useState(formData && formData.steps ? '' : []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const newStep = {
      description,
    };
    setFormData((prevFormData) => ({
      ...prevFormData,
      steps: [...(prevFormData?.steps || []), newStep],
    }));
    setDescription("");
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
                <button type="submit">Add Step</button>
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
              <li key={index}>{step.description}</li>
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
