import React, { createContext, useContext, useState } from 'react';

const GroceryListContext = createContext();

export function useGroceryList() {
	return useContext(GroceryListContext);
}

export function GroceryListProvider({ children }) {
	const [groceryList, setGroceryList] = useState([]);

	return (
		<GroceryListContext.Provider value={{ groceryList, setGroceryList }}>
			{children}
		</GroceryListContext.Provider>
	);
}
