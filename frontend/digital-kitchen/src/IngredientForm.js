import React, { useState } from "react";
import axios from "axios";

function IngredientForm({ recipeId }) {
  const [name, setName] = useState("");
  const [quantity, setQuantity] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      name: name,
      quantity: quantity,
      recipeId: recipeId,
    };
    axios.post("http://localhost:8080/digitalkitchen/ingredients/createIngredient", data)
      .then((response) => {
        console.log(response);
        setName("");
        setQuantity("");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <h2>Add Ingredient</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
        <div>
          <label>Quantity:</label>
          <input type="text" value={quantity} onChange={(e) => setQuantity(e.target.value)} required />
        </div>
        <button type="submit">Add Ingredient</button>
      </form>
    </div>
  );
}

export default IngredientForm;
