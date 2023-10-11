import React, { createContext, useContext, useState, useEffect } from 'react';
import FetchManager from '../components/util/FetchManager';

const TagsContext = createContext();

export function useTagsList() {
  return useContext(TagsContext);
}

export function TagsProvider({ children }) {
    const [tagsList, setTagsList] = useState([]);

    useEffect(() => {
        FetchManager.fetchFormTagsList()
          .then((data) => {
            setTagsList(data);
          })
          .catch((error) => {
            console.log(error);
          });
      }, []);
      
      

    return (
        <TagsContext.Provider value={{ tagsList, setTagsList }}>
            {children}
        </TagsContext.Provider>
    );
}
