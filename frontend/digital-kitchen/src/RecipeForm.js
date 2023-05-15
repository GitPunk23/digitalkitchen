import React, { useState, useEffect } from 'react';
import axios from 'axios';

const RecipeForm = () => {
  const [name, setName] = useState('');
  const [author, setAuthor] = useState('');
  const [description, setDescription] = useState('');
  const [servings, setServings] = useState('');
  const [caloriesPerServing, setCaloriesPerServing] = useState('');
  const [notes, setNotes] = useState('');
  const [category, setCategory] = useState('');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/digitalkitchen/recipes/categories')
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = {
      name,
      description,
      servings,
      caloriesPerServing,
      notes,
      category,
    };
    axios.post('http://localhost:8080/digitalkitchen/recipes/createRecipe', JSON.stringify(data), {
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input type="text" value={name} onChange={(event) => setName(event.target.value)} />
      </label>
      <label>
        Author:
        <input type="text" value={name} onChange={(event) => setAuthor(event.target.value)} />
      </label>
      <label>
        Description:
        <input type="text" value={description} onChange={(event) => setDescription(event.target.value)} />
      </label>
      <label>
        Servings:
        <input type="number" value={servings} onChange={(event) => setServings(event.target.value)} />
      </label>
      <label>
        Calories per serving:
        <input type="number" value={caloriesPerServing} onChange={(event) => setCaloriesPerServing(event.target.value)} />
      </label>
      <label>
        Notes:
        <textarea value={notes} onChange={(event) => setNotes(event.target.value)} />
      </label>
      <label>
        Category:
        <select value={category} onChange={(event) => setCategory(event.target.value)}>
          <option value="">--Select a category--</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </label>
      <button type="submit">Submit</button>
    </form>
  );
};

export default RecipeForm;
