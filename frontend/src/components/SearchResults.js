import Table from 'react-bootstrap/Table';
import React, { useState, useEffect } from 'react';
import DisplayPage from './pages/DisplayPage';
import SearchResultsDisplay from './SearchResultsDisplay';
import { Button } from 'react-bootstrap';

const SearchResults = ({ results, switchDisplayView }) => {
  const [recipes, setRecipes] = useState([]);
  const [useTableView, setUseTableView] = useState(true);
  const [currentRecipe, setCurrentRecipe] = useState(null);

  useEffect(() => {
    setRecipes(results);
  }, [results]);

  const handleRowClick = (recipe) => {
    setCurrentRecipe(recipe);
    setUseTableView(false);
  }

  const handleBackToTable = () => {
    console.log("hi")
    setCurrentRecipe(null);
    setUseTableView(true);
  }

  const renderDisplay = () => {
    return (
      <>
        <Button onClick={handleBackToTable}>back</Button>
        <SearchResultsDisplay 
          recipes={recipes} 
          currentRecipeID={recipes.findIndex((recipe) => recipe.id === currentRecipe.id)}/>
      </>
    )
  }

  const renderTable = () => {
    return (
      <Table striped bordered hover variant="dark">
          <thead>
            <tr>
              <th>Recipe</th>
              <th>Author</th>
              <th>Category</th>
            </tr>
          </thead>
          <tbody>
            {recipes.map((recipe) => (
              <tr key={recipe.id} onClick={() => handleRowClick(recipe)}>
                <td>{recipe.name}</td>
                <td>{recipe.author}</td>
                <td>{recipe.category}</td>
              </tr>
            ))}
          </tbody>
        </Table>
    )
  }

  return (
    <>
      {recipes.length > 0 && useTableView && renderTable()}
      {!useTableView && renderDisplay()}
    </>
  );
};

export default SearchResults;
