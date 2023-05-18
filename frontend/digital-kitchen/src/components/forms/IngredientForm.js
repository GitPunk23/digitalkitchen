import React, { useState, useEffect } from "react";
import axios from "axios";

function IngredientForm({ formData, setFormData }) {
  const [ingredients, setIngredients] = useState(formData && formData.ingredients ? formData.ingredients : []);
  const [ingredient, setIngredient] = useState("");
  const [quantity, setQuantity] = useState("");
  const [measurement, setMeasurement] = useState("");
  const [notes, setNotes] = useState("");
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
    setIngredient("");
    setQuantity("");
    setMeasurement("");
    setNotes("");
  };

  const handleEditIngredient = (index) => {
    const ingredient = ingredients[index];
    setIngredient(ingredient.ingredient);
    setQuantity(ingredient.quantity);
    setMeasurement(ingredient.measurement);
    setNotes(ingredient.notes);
    setIngredients((prevIngredients) => {
      const updatedIngredients = [...prevIngredients];
      updatedIngredients.splice(index, 1);
      return updatedIngredients;
    });
  };

  const handleDeleteIngredient = (index) => {
    setIngredients((prevIngredients) => {
      const updatedIngredients = [...prevIngredients];
      updatedIngredients.splice(index, 1);
      return updatedIngredients;
    });
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
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <input
                  type="text"
                  value={ingredient}
                  onChange={(e) => setIngredient(e.target.value)}
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
              <td>
                <button type="submit">
                  {ingredient ? "Update Ingredient" : "Add Ingredient"}
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div>
        <h3>Ingredients:</h3>
        {ingredients.length > 0 ? (
          <ul>
            {ingredients.map((ingredient, index) => (
              <li key={index}>
                {ingredient.quantity} {ingredient.measurement} {ingredient.ingredient} - {ingredient.notes}
                <button onClick={() => handleEditIngredient(index)}>Edit</button>
                <button onClick={() => handleDeleteIngredient(index)}>Delete</button>
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
