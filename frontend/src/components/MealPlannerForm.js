import { Form, Button, ListGroup, Table, Card } from 'react-bootstrap';
import React, { useState, useEffect } from 'react';
import AutosuggestTextBox from './forms/AutosuggestTextBox';
import SearchBar from './SearchBar';
import { useTagsList } from '../context/TagsContext';

const MealPlannerForm = ({mealPlanFormat}) => {

    const { tagsList } = useTagsList();
    const [mealPlanData, setMealPlanData] = useState(mealPlanFormat)
    const typeList = ["breakfast", "lunch", "dinner", "snack", "meal"];
    const [recipeResults, setRecipeResults] = useState([])

    const handleRecipeSearch = (results) => {
        setRecipeResults(results);
    }

    const handleAddTag = (tag) => {
		if (tag === '') {
		  	return;
		}
		setMealPlanData((prevMealPlanData) => ({
		  	...prevMealPlanData,
		  	tags: [...prevMealPlanData.tags, tag],
		}));
	};

    const handleDeleteTag = (index) => {
		const Tags = [...mealPlanData.tags];
		Tags.splice(index, 1);

		setMealPlanData(prevMealPlanData => ({
			...prevMealPlanData,
			tags: Tags,
		}));
	};

    const handleChange = (e) => {
		const { name, value } = e.target;
		setMealPlanData(prevData => ({
			...prevData,
			[name]: value,
		}));
  	};

      const handleRecipeClick = (recipe) => {
        const isSelected = mealPlanData.recipes.some((selectedRecipe) => selectedRecipe.id === recipe.id);
      
        if (isSelected) {
          // If the recipe ID is already selected, remove it from the list
          setMealPlanData((prevData) => ({
            ...prevData,
            recipes: prevData.recipes.filter((selectedRecipe) => selectedRecipe.id !== recipe.id),
          }));
        } else {
          // If the recipe ID is not selected, add it to the list (only ID, not name)
          setMealPlanData((prevData) => ({
            ...prevData,
            recipes: [...prevData.recipes, recipe.id],
          }));
        }
      };      
      

    const submitMeal = () => {
        console.log(mealPlanData);
    }

    return (
        <div>
        <Form>
            <Form.Group controlId='date'>
                <Form.Label>Date</Form.Label>
                <Form.Control
                    type="date"
                    name="date"
                    value={mealPlanData.date}
					onChange={handleChange}
                />
            </Form.Group>
            <Form.Group controlId='name'>
                <Form.Label>Name</Form.Label>
                <Form.Control
                    type="text"
                    name="name"
                    value={mealPlanData.name}
					onChange={handleChange}
                ></Form.Control>
            </Form.Group>

            <Form.Group controlId='type'>
                <Form.Label>Type</Form.Label>
                <Form.Control
                    as="select"
                    name="type"
                    value={mealPlanData.type}
                    onChange={(e) => {
                        handleChange(e);
                    }}
                >
					<option value="">Select Type</option>
					{typeList.map((option, index) => (
						<option key={index} value={option}>
							{option}
						</option>
					))}
				</Form.Control>
            </Form.Group>

            <Form.Group controlId='notes'>
                <Form.Label>Notes</Form.Label>
                <Form.Control
                    as="textarea"
                    name="notes"
                    value={mealPlanData.notes}
					onChange={handleChange}
                ></Form.Control>
            </Form.Group>

            <Form.Group controlId="mealTags">
				<Form.Label><strong>Tags</strong></Form.Label>
				<div className="tag-container">
					<AutosuggestTextBox
						data={tagsList} 
						setValue={(value) => handleAddTag(value)}
						returnSuggestion={(value) => handleAddTag(value)}
						placeholder={"Enter tag name..."}
						clearInputOnSubmit={true}
					></AutosuggestTextBox>
					{mealPlanData.tags.map((tag, index) => (
						<div key={index} className="tag">
							<span>{tag}</span>
							<Button
								variant="danger"
								size="sm"
								onClick={() => handleDeleteTag(index)}
							>
								X
							</Button>
						</div>
					))}
				</div>
			</Form.Group>
        </Form>

        <div>
            <Form.Group>
                <Form.Label>Recipes</Form.Label>
                <SearchBar setResults={handleRecipeSearch} />
            </Form.Group>

            <div className="recipe-list">
                {recipeResults.map((recipe) => (
                <Card key={recipe.id} onClick={() => handleRecipeClick(recipe)}>
                    <Card.Body>
                    <Card.Title>{recipe.name}</Card.Title>
                    </Card.Body>
                </Card>
                ))}
            </div>
        </div>
        {mealPlanData.recipes.length > 0 && (
        <div>
          <h2>Selected Recipes for Meal Plan</h2>
          <ul>
            {mealPlanData.recipes.map((selectedRecipe) => (
              <li key={selectedRecipe.id}>{selectedRecipe.name}</li>
            ))}
          </ul>
          {/* Add more details or render the selected recipes for the meal plan as needed */}
        </div>
        )}
        <div>
            <Button variant="primary" onClick={() => submitMeal()}>
                Save
            </Button>
            <Button>
                Cancel
            </Button>
        </div>
        </div>
    );
}

export default MealPlannerForm;