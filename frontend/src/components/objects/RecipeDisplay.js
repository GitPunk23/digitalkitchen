import React, { Component } from 'react';
import { Form, Button, ListGroup, Table } from 'react-bootstrap';

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

  handleSubmit = async (e) => {
    e.preventDefault();
    const { formData } = this.state;
    this.setState({ isEditing: false });
    console.log("Submitted updated data:",formData);

    try {
      const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/update`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      const json = await response.json();

      if (response.status === 200) {
        console.log(json);
      }
      
  
    } catch (error) {
      console.log(error)
    }

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

            <Form.Group controlId="recipeAuthor">
              <Form.Label>Author</Form.Label>
              <Form.Control
                type="text"
                name="author"
                value={formData.author}
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

            <Form.Group controlId="recipeIngredients">
              <Form.Label><strong>Ingredients</strong></Form.Label>
              <Table striped bordered>
                <thead>
                  <tr>
                    <th>Ingredient</th>
                    <th>Measurement</th>
                    <th>Quantity</th>
                    <th>Notes</th>
                  </tr>
                </thead>
                <tbody>
                  {formData.ingredients.map((ingredient, index) => (
                    <tr key={index}>
                      <td>
                        <Form.Control
                        type="text"
                        name="ingredient"
                        value={ingredient.ingredient}
                        onChange={this.handleChange}
                        />
                        </td>
                      <td>
                        <Form.Control
                          type="text"
                          name="measurement"
                          value={ingredient.measurement}
                          onChange={this.handleChange}
                        />
                      </td>
                      <td>
                        <Form.Control
                          type="number"
                          name="quantity"
                          value={ingredient.quantity}
                          onChange={this.handleChange}
                        />
                      </td>
                      <td>
                        <Form.Control
                          type="text"
                          name="notes"
                          value={ingredient.notes}
                          onChange={this.handleChange}
                        />
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </Form.Group>

            <Form.Group controlId="recipeSteps">
              <Form.Label><strong>Steps</strong></Form.Label>
              <ListGroup>
                {formData.steps.map((step, index) => (
                  <ListGroup.Item key={index}>
                    {step.stepNumber}. 
                    <Form.Control
                      type="text"
                      name="caloriesPerServing"
                      value={step.description}
                      onChange={this.handleChange}/>
                  </ListGroup.Item>
                ))}
              </ListGroup>
            </Form.Group>

            <Form.Group controlId="recipeTags">
              <Form.Label><strong>Tags</strong></Form.Label>
              <ListGroup>
                {formData.tags.map((tag, index) => (
                  <ListGroup.Item key={index}>
                    {tag}
                    <Form.Control
                    type="text"
                    name="tags"
                    value={tag}
                    onChange={this.handleChange}/>
                    </ListGroup.Item>
                ))}
              </ListGroup>
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
            <strong>Author:</strong> {formData.author}
          </p>
          <p>
            <strong>Category:</strong> {formData.category}
          </p>
          <p>
            <strong>Description:</strong> {formData.description}
          </p>
          <p>
            <strong>Servings:</strong> {formData.servings}
          </p>
          <p>
            <strong>Calories per Serving:</strong> {formData.caloriesPerServing}
          </p>
          <p>
            <strong>Notes:</strong> {formData.notes}
          </p>

          <div>
            <strong>Ingredients:</strong>
            <Table striped bordered>
              <thead>
                <tr>
                  <th>Ingredient</th>
                  <th>Quantity</th>
                  <th>Measurement</th>
                  <th>Notes</th>
                </tr>
              </thead>
              <tbody>
                {(formData.ingredients || []).map((ingredient, index) => (
                  <tr key={index}>
                    <td>{ingredient.ingredient}</td>
                    <td>{ingredient.quantity}</td>
                    <td>{ingredient.measurement}</td>
                    <td>{ingredient.notes}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>

          <div>
            <strong>Steps:</strong>
            <ol>
              {(formData.steps || []).map((step, index) => (
                <li key={index}>
                  {`${step.description}`}
                </li>
              ))}
            </ol>
          </div>

          <div>
            <strong>Tags:</strong>
            <ul>
              {(formData.tags || []).map((tag, index) => (
                <li key={index}>{tag}</li>
              ))}
            </ul>
          </div>
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
