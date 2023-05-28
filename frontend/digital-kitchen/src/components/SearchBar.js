import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';

const SearchBar = ({ setResults }) => {
  const [name, setName] = useState('');
  const [ingredients, setIngredients] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [categories, setCategories] = useState([]);
  const [servings, setServings] = useState('');
  const [calories, setCalories] = useState('');
  const [tags, setTags] = useState([]);
  const [isFiltering, setIsFiltering] = useState(false);
  const [categorySuggestions, setCategorySuggestions] = useState([]);
  const [authorSuggestions, setAuthorSuggestions] = useState([]);
  const [ingredientsList, setIngredientsList] = useState([]);
  const [tagsList, setTagsList] = useState([]);
  const [ingredientSuggestions, setIngredientSuggestions] = useState([]);
  const [tagsSuggestions, setTagsSuggestions] = useState([]);
  const [showingIngredientsDropdown, setShowingIngredientsDropdown] = useState(false);
  const [showingTagsDropdown, setShowingTagsDropdown] = useState(false);

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  //Category List
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
        setCategorySuggestions(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  //Author List
  useEffect(() => {
    fetch('http://localhost:8080/digitalkitchen/form/authors')
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

  //Tag suggestions
  useEffect(() => {
    fetch('http://localhost:8080/digitalkitchen/form/tags')
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error retrieving tags');
        }
      })
      .then((data) => {
        setTagsList(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  
  //Ingredients suggestions
  useEffect(() => {
    fetch('http://localhost:8080/digitalkitchen/form/ingredients')
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error retrieving ingredients');
        }
      })
      .then((data) => {
        setIngredientsList(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  

  // FILTERS
  const handleInputKeyDown = (event) => {
    if (event.key === 'Enter' || event.key === ',') {
      event.preventDefault();
      const trimmedValue = event.target.value.trim();
      console.log(event.target.name)
  
      if (trimmedValue !== '' && event.target.name === 'ingredients') {
        setIngredients([...ingredients, trimmedValue]);
      }
      if (trimmedValue !== '' && event.target.name === 'tags') {
        setTags([...tags, trimmedValue]);
      }
      
      event.target.value = ''; // Clear the input value after adding the tag
    }
  };  

  const handleIngredientInputChange = async (event) => {
    const value = event.target.value;
    setIngredientSuggestions(ingredientsList.filter((ingredient) => 
      ingredient.startsWith(value)))
  }

  const handleSelectIngredientSuggestion = (suggestion) => {
    setIngredients([...ingredients, suggestion])
  }

  const handleRemoveIngredient = (removed) => {
    let newIngredients = ingredients.filter((ingredient) => ingredient !== removed);
    setIngredients(newIngredients);
  }

  const handleAuthorAdd = (event) => {
    const newAuthor = event.target.value.trim();
    if (newAuthor !== '' && !authors.includes(newAuthor)) {
      setAuthors([...authors, newAuthor]);
      event.target.value = '';
    }
  };

  const handleRemoveAuthor = (removed) => {
    let newAuthors = authors.filter((author) => author !== removed);
    setAuthors(newAuthors);
  }

  const handleCategoryAdd = (event) => {
    const newCategory = event.target.value.trim();
    if (newCategory !== '' && !categories.includes(newCategory)) {
      setCategories([...categories, newCategory]);
      event.target.value = '';
    }
  };

  const handleRemoveCategory = (removed) => {
    let newCategories = categories.filter((category) => category !== removed);
    setCategories(newCategories);
  }

  const handleServingsAdd = (event) => {
    setServings(event.target.value);
  }

  const handleCaloriesAdd = (event) => {
    setCalories(event.target.value);
  }

  const handleSelectTagSuggestion = (suggestion) => {
    setTags([...tags, suggestion]);
  };

  const handleTagInputChange = async (event) => {
    const value = event.target.value;
    setTagsSuggestions(tagsList.filter((tag) => 
      tag.startsWith(value)))
  }

  const handleRemoveTag = (removed) => {
    let newTags = tags.filter((tag) => tag !== removed);
    setTags(newTags);
  }

  const handleFilter = (event) => {
    setIsFiltering(!isFiltering);
  }

  const removeFilters = (event) => {
    setIngredients([]);
    setAuthors([]);
    setCategories([]);
    setServings('');
    setCalories('');
    setTags([]);
  };
  

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Prepare the search criteria JSON object
    const searchCriteria = {
      name: name,
      authors: authors,
      categories: categories,
      servings: servings,
      calories: calories,
      ingredients: ingredients,
      tags: tags,
    };

    console.log("Searching for: ",searchCriteria);

    try {
        const response = await fetch('http://localhost:8080/digitalkitchen/search', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(searchCriteria),
        });

        const json = await response.json();

        if (response.status === 200) {
          setResults(json);
        }
        
    
    } catch (error) {

    }
  };

  return (
    <div>
      <Form onSubmit={handleSubmit}>
      <Form.Group className="name">
        <Form.Control
          type="text"
          placeholder="Enter recipe name"
          onChange={handleNameChange}
        />
        <Button type="button" onClick={handleFilter}>Filter</Button>
        <Button type="submit">Search</Button>
      </Form.Group>

      {isFiltering && (
      <div >
        {/*INGREDIENTS*/}
        <Form.Group className="ingredients">
          <label>Ingredients:</label>
            <Form.Control 
              name="ingredients"
              type="Select" 
              className="form-control" 
              placeholder="Press enter after each"
              onFocus={() => setShowingIngredientsDropdown(true)}
              onBlur={() => setShowingIngredientsDropdown(false)}
              onKeyDown={(event) => 
              handleInputKeyDown(event)}
              onChange={(event) => 
                handleIngredientInputChange(event)}
              />
              {(ingredientSuggestions.length > 0) && (showingIngredientsDropdown) && (
                <ul className="suggestions-dropdown">
                  {ingredientSuggestions.slice(0, 5).map((suggestion, index) => (
                    <li key={index} onClick={() => handleSelectIngredientSuggestion(suggestion)}>
                      {suggestion}
                    </li>
                  ))}
                </ul>
              )}
          </Form.Group>


        <div>
          {ingredients.length > 0 && (
          <div>
            {ingredients.map((ingredient, index) => (
            <span key={index} className="ingredient">
              <Button href="#" className="ingredients" />
                {ingredient}
              <Button
                variant="danger"
                size="sm"
                onClick={() => handleRemoveIngredient(ingredient)}
              >
                x
              </Button>
            </span>
            ))}
          </div>
          )}
        </div>

        {/*AUTHORS*/}
        <Form.Group>
          <label>Authors:</label>
          <Form.Control
            as="select"
            value={authors}
            onChange={handleAuthorAdd}
          >
            <option value="">--select author--</option>
            {authorSuggestions.map((author) => (
            <option key={author} value={author}>
                {author}
            </option>
            ))}
          </Form.Control>
        </Form.Group>

        <div>
          {authors.length > 0 && (
          <div>
            {authors.map((author, index) => (
            <span key={author} className="author">
              <Button href="#" className="authors" />
                {author}
              <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleRemoveAuthor(author)}
              >
                x
              </Button>
            </span>
            ))}
          </div>
          )}
        </div>

        {/*CATEGORIES*/}
        <Form.Group>
          <label>Categories:</label>
          <Form.Control
            as="select"
            value={categories}
            onChange={handleCategoryAdd}
          >
            <option value="">--select category--</option>
            {categorySuggestions.map((category) => (
            <option key={category.id} value={category.name}>
                {category.name}
            </option>
            ))}
          </Form.Control>
        </Form.Group>

        <div>
          {(categories.length > 0) && (
          <div>
            {categories.map((category, index) => (
            <span key={category.name} className="category">
              <Button href="#" className="categories" />
                {category}
              <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleRemoveCategory(category)}
              >
                x
              </Button>
            </span>
            ))}
          </div>
          )}
        </div>

        {/*SERVINGS*/}
        <Form.Group className="servings">
          <label>Servings:</label>
          <Form.Control
            type="number" 
            className="form-control" 
            placeholder=""
            onChange={handleServingsAdd}
          />
        </Form.Group>

        {/*CALORIES*/}
        <Form.Group className="calories">
          <label>Calories:</label>
          <Form.Control 
            type="number" 
            className="form-control" 
            col="3"
            onChange={handleCaloriesAdd}
          />
        </Form.Group>

        {/*TAGS*/}
        <Form.Group className="tags">
          <label>Tags</label>
          <Form.Control
            name="tags"
            type="select" 
            className="form-control" 
            placeholder="Press enter after each"
            onFocus={() => setShowingTagsDropdown(true)}
            onBlur={() => setShowingTagsDropdown(false)}
            onKeyDown={(event) => 
              handleInputKeyDown(event)}
            onChange={(event) => 
              handleTagInputChange(event)}
          />
          {(tagsSuggestions.length > 0) && (showingTagsDropdown) && (
            <ul className="suggestions-dropdown">
              {tagsSuggestions.slice(0, 5).map((suggestion, index) => (
                <li key={index} onClick={() => handleSelectTagSuggestion(suggestion)}>
                  {suggestion}
                </li>
              ))}
            </ul>
          )}
        </Form.Group>

        <div>
          {tags.length > 0 && (
          <div>
            {tags.map((tag, index) => (
            <span key={index} className="tag">
              <Button href="#" className="tags" />
                {tag}
              <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleRemoveTag(tag)}
              >
                x
              </Button>
            </span>
            ))}
          </div>
          )}
        </div>
        <Button onClick={removeFilters}>Remove</Button>
      </div>
        )}
      </Form>
    </div>
  );
};

export default SearchBar;
