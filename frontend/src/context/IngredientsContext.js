import React, { createContext, useContext, useState, useEffect } from 'react';
import FetchManager from '../components/util/FetchManager';

const IngredientsContext = createContext();

export function useIngredientsList() {
  	return useContext(IngredientsContext);
}

export function IngredientsProvider({ children }) {
	const [ingredientsList, setIngredientsList] = useState([]);

	useEffect(() => {
		FetchManager.fetchFormIngredientsList()
		.then((data) => {
			setIngredientsList(data);
		})
		.catch((error) => {
			console.log(error);
		});
	}, []);

	return (
		<IngredientsContext.Provider value={{ ingredientsList, setIngredientsList }}>
		{children}
		</IngredientsContext.Provider>
	);
}
