import React, { useState, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
//import '../../styles/RecipeForm.css';

const RecipeForm = ({ onNextStep, formData, setFormData }) => {
  const [name, setName] = useState(formData?.recipe?.name || '');
  const [author, setAuthor] = useState(formData?.recipe?.author || '');
  const [description, setDescription] = useState(formData?.recipe?.description || '');
  const [servings, setServings] = useState(formData?.recipe?.servings || '');
  const [caloriesPerServing, setCaloriesPerServing] = useState(formData?.recipe?.caloriesPerServing || '');
  const [notes, setNotes] = useState(formData?.recipe?.notes || '');
  const [category, setCategory] = useState(formData?.recipe?.category || '');
  const [categories, setCategories] = useState([]);
  const [authorSuggestions, setAuthorSuggestions] = useState([]);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/categories`)
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

  //Author List
  useEffect(() => {
    fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/authors`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error retrieving authors');
        }
      })
      .then((data) => {
        setAuthorSuggestions(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setFormData((prevFormData) => ({
      ...prevFormData,
      recipe: {
        ...prevFormData.recipe,
        name,
        description,
        servings,
        caloriesPerServing,
        notes,
        category,
        author,
      },
    }));
  }, [name, description, servings, caloriesPerServing, notes, category, author, setFormData]);

  const handleSubmit = (event) => {
    event.preventDefault();
    onNextStep();
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formName">
        <Form.Label>Name:</Form.Label>
        <Form.Control
          type="text"
          value={name}
          onChange={(event) => setName(event.target.value)}
          maxLength={50}
          required
        />
      </Form.Group>
      <Form.Group controlId="formAuthor">
        <Form.Label>Author:</Form.Label>
        <Form.Control
          as="input"
          list="authorSuggestions"
          value={author}
          onChange={(event) => setAuthor(event.target.value)}
          required
        />
        <datalist id="authorSuggestions">
          {authorSuggestions.map((author) => (
            <option key={author} value={author} />
          ))}
        </datalist>
      </Form.Group>


      <Form.Group controlId="formDescription">
        <Form.Label>Description:</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          value={description}
          onChange={(event) => setDescription(event.target.value)}
          maxLength={500}
        />
      </Form.Group>
      <Form.Group controlId="formServings">
        <Form.Label>Servings:</Form.Label>
        <Form.Control
          type="number"
          value={servings}
          onChange={(event) => setServings(event.target.value)}
          min={0}
          step={1}
          pattern="[0-9]*"
          inputMode="numeric"
        />
      </Form.Group>
      <Form.Group controlId="formCalories">
        <Form.Label>Calories per serving:</Form.Label>
        <Form.Control
          type="number"
          value={caloriesPerServing}
          onChange={(event) => setCaloriesPerServing(event.target.value)}
          min={0}
          step={1}
          pattern="[0-9]*"
          inputMode="numeric"
        />
      </Form.Group>
      <Form.Group controlId="formNotes">
        <Form.Label>Notes:</Form.Label>
        <Form.Control
          as="textarea"
          value={notes}
          onChange={(event) => setNotes(event.target.value)}
          maxLength={200}
        />
      </Form.Group>
      <Form.Group controlId="formCategory">
        <Form.Label>Category:</Form.Label>
        <Form.Control
          as="select"
          value={category}
          onChange={(event) => setCategory(event.target.value)}
          required
        >
          <option value="">--Select a category--</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </Form.Control>
      </Form.Group>
    </Form>
  );
};

export default RecipeForm;
