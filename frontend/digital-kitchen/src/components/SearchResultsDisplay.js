import DisplayPage from "./pages/DisplayPage";
import React from 'react';

const SearchResultsDisplay = ({ recipes, currentRecipe }) => {
    return (
        <div>
            <DisplayPage data={currentRecipe}/>
        </div>
    );
};

export default SearchResultsDisplay;
