import React, { createContext, useContext, useState, useEffect } from 'react';
import FetchManager from '../components/util/FetchManager';

const CategoryContext = createContext();

export function useCategoriesList() {
  return useContext(CategoryContext);
}

export function CategoriesProvider({ children }) {
    const [categoriesList, setCategoriesList] = useState([]);

    useEffect(() => {
        FetchManager.fetchFormCategoriesList()
          .then((data) => {
            const categories = data.map((category) => category.name); 
            setCategoriesList(categories);
          })
          .catch((error) => {
            console.log(error);
          });
      }, []);
      
      

    return (
        <CategoryContext.Provider value={{ categoriesList, setCategoriesList }}>
            {children}
        </CategoryContext.Provider>
    );
}
