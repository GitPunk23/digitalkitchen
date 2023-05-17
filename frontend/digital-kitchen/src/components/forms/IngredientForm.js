import React, { useState, useEffect } from "react";
import axios from "axios";

function IngredientForm({ formData, setFormData }) {
  const [ingredients, setIngredients] = useState(formData && formData.ingredients ? formData.ingredients : []);
  const [ingredient, setName] = useState(formData && formData.ingredient ? formData.ingredient : "");
  const [quantity, setQuantity] = useState(formData && formData.quantity ? formData.quantity : "");
  const [measurement, setMeasurement] = useState(formData && formData.measurement ? formData.measurement : "");
  const [notes, setNotes] = useState(formData && formData.notes ? formData.notes : "");
  const [measurements, setMeasurements] = useState([]);

  useEffect(() => {
    // Fetch measurements from the backend
    axios
      .get("http://localhost:8080/digitalkitchen/form/measurements")
      .then((response) => {
        setMeasurements(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const newIngredient = {
      ingredient,
      quantity,
      measurement,
      notes,
    };
    setIngredients([...ingredients, newIngredient]);
    setName("");
    setQuantity("");
    setMeasurement("");
    setNotes("");
  };

  useEffect(() => {
    setFormData((prevFormData) => ({
      ...prevFormData,
      ingredients: ingredients,
    }));
  }, [ingredients, setFormData]);

  return (
    <div>
      <h2>Add Ingredients</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Quantity</th>
              <th>Measurement</th>
              <th>Notes</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <input
                  type="text"
                  value={ingredient}
                  onChange={(e) => setName(e.target.value)}
                  required
                />
              </td>
              <td>
                <input
                  type="number"
                  step="0.01"
                  value={quantity}
                  onChange={(e) => setQuantity(e.target.value)}
                  required
                />
              </td>
              <td>
                <select
                  value={measurement}
                  onChange={(e) => setMeasurement(e.target.value)}
                  required
                >
                  <option value="">-- Select a measurement --</option>
                  {measurements.map((measurement) => (
                    <option
                      key={measurement.id}
                      value={measurement.id}
                    >
                      {measurement.measurement}
                    </option>
                  ))}
                </select>
              </td>
              <td>
                <input
                  type="text"
                  value={notes}
                  onChange={(e) => setNotes(e.target.value)}
                />
              </td>
            </tr>
          </tbody>
        </table>
        <button type="submit">Add Ingredient</button>
      </form>
      <div>
        <h3>Ingredients:</h3>
        {ingredients.length > 0 ? (
          <ul>
            {ingredients.map((ingredient, index) => (
              <li key={index}>
                {ingredient.quantity} {ingredient.measurement} {ingredient.ingredient} - {ingredient.notes}
              </li>
            ))}
          </ul>
        ) : (
          <p>No ingredients added yet.</p>
        )}
      </div>
    </div>
  );
}

export default IngredientForm;
