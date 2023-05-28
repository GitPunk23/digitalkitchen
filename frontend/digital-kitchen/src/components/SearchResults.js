import Table from 'react-bootstrap/Table';
import React, { useState, useEffect } from 'react';
import DisplayPage from './pages/DisplayPage';
import SearchResultsDisplay from './SearchResultsDisplay';

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
    setCurrentRecipe(null);
    setUseTableView(true);
  }

  const renderDisplay = () => {
    return (
      <SearchResultsDisplay recipes={recipes} currentRecipe={currentRecipe}/>
    )
  }

  return (
    <>
      {recipes.length > 0 && useTableView && (
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
      )}
      {!useTableView && renderDisplay()}
    </>
  );
};

export default SearchResults;
