import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';

class RecipeDisplay extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isEditing: false,
      formData: { ...props.formData },
    };
  }

  handleEdit = () => {
    this.setState({ isEditing: true });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { formData } = this.state;
    // Perform form submission to update the recipe details
    // You can use the formData object to send the updated data to the backend
    console.log(formData);
    // Reset the editing state
    this.setState({ isEditing: false });
  };

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      formData: {
        ...prevState.formData,
        [name]: value,
      },
    }));
  };

  render() {
    const { isEditing, formData } = this.state;

    return (
      <div>
        <h2>Recipe Details</h2>
        {isEditing ? (
          <Form onSubmit={this.handleSubmit}>
            <Form.Group controlId="recipeName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={formData.name}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Form.Group controlId="recipeDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control
                as="textarea"
                name="description"
                value={formData.description}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Form.Group controlId="recipeServings">
              <Form.Label>Servings</Form.Label>
              <Form.Control
                type="number"
                name="servings"
                value={formData.servings}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Form.Group controlId="recipeCaloriesPerServing">
              <Form.Label>Calories per Serving</Form.Label>
              <Form.Control
                type="number"
                name="caloriesPerServing"
                value={formData.caloriesPerServing}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Form.Group controlId="recipeNotes">
              <Form.Label>Notes</Form.Label>
              <Form.Control
                as="textarea"
                name="notes"
                value={formData.notes}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Form.Group controlId="recipeAuthor">
              <Form.Label>Author</Form.Label>
              <Form.Control
                type="text"
                name="author"
                value={formData.author}
                onChange={this.handleChange}
              />
            </Form.Group>

            <Button variant="primary" type="submit">
              Save
            </Button>
          </Form>
        ) : (
          <div>
            <p>
              <strong>Name:</strong> {formData.name}
            </p>
            <p>
              <strong>Description:</strong> {formData.description}
            </p>
            <p>
              <strong>Servings:</strong> {formData.servings}
            </p>
            <p>
              <strong>Calories per Serving:</strong> 
              <>{formData.caloriesPerServing}</>
            </p>
            <p>
              <strong>Notes:</strong> {formData.notes}
            </p>
            <p>
              <strong>Author:</strong> {formData.author}
            </p>
          </div>
        )}

        {!isEditing && (
          <Button variant="secondary" onClick={this.handleEdit}>
            Edit
          </Button>
        )}
      </div>
    );
  }
}

export default RecipeDisplay;
