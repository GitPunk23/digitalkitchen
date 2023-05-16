import React, { useState, useEffect } from 'react';
import '../../styles/RecipeForm.css';

const RecipeForm = ({ onNextStep, formData, setFormData }) => {
  const [name, setName] = useState(formData && formData.name ? formData.name : '');
  const [author, setAuthor] = useState(formData && formData.author ? formData.author : '');
  const [description, setDescription] = useState(formData && formData.description ? formData.description : '');
  const [servings, setServings] = useState(formData && formData.servings ? formData.servings : '');
  const [caloriesPerServing, setCaloriesPerServing] = useState(formData && formData.caloriesPerServing ? formData.caloriesPerServing : '');
  const [notes, setNotes] = useState(formData && formData.notes ? formData.notes : '');
  const [category, setCategory] = useState(formData && formData.category ? formData.category : '');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/digitalkitchen/form/categories')
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error retrieving categories');
        }
      })
      .then((data) => {
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setFormData((prevFormData) => ({
      ...prevFormData,
      recipe: {
        name,
        description,
        servings,
        caloriesPerServing,
        notes,
        category,
        author,
      },
    }));
  }, [name, description, servings, caloriesPerServing, notes, category, author]);
    
  

  const handleSubmit = (event) => {
    event.preventDefault();
    const updatedFormData = {
      ...formData,
      recipe: {
        name,
        description,
        servings,
        caloriesPerServing,
        notes,
        category,
        author,
      },
    };
    setFormData(updatedFormData);
    onNextStep();
  };  
  

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input
          className="name"
          type="text"
          value={name}
          onChange={(event) => setName(event.target.value)}
        />
      </label>
      <label>
        Author:
        <input
          className="author"
          type="text"
          value={author}
          onChange={(event) => setAuthor(event.target.value)}
        />
      </label>
      <label>
        Description:
        <textarea
          className="description"
          rows={3}
          value={description}
          onChange={(event) => setDescription(event.target.value)}
        />
      </label>
      <label>
        Servings:
        <input
          className="servings"
          type="number"
          value={servings}
          onChange={(event) => setServings(event.target.value)}
        />
      </label>
      <label>
        Calories per serving:
        <input
          className="calories"
          type="number"
          value={caloriesPerServing}
          onChange={(event) => setCaloriesPerServing(event.target.value)}
        />
      </label>
      <label>
        Notes:
        <textarea
          className="notes"
          value={notes}
          onChange={(event) => setNotes(event.target.value)}
        />
      </label>
      <label>
        Category:
        <select
          className="category"
          value={category}
          onChange={(event) => setCategory(event.target.value)}
        >
          <option value="">--Select a category--</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </label>
    </form>
  );
};

export default RecipeForm;
