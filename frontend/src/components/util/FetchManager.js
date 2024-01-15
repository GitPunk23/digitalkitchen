const fetchFormIngredientsList = async () => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/ingredients`);
        if (!response.ok) {
            throw new Error('Error retrieving ingredients');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.log('Fetch error:', error);
        throw error;
    }
};

const fetchFormMeasurementsList = async () => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/measurements`);
        if (!response.ok) {
            throw new Error('Error retrieving measurements');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
};

const fetchFormTagsList = async () => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/tags`);
        if (!response.ok) {
            throw new Error('Error retrieving tags');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
};

const fetchFormAuthorsList = async() => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/authors`);
        if (!response.ok) {
            throw new Error('Error retrieving authors');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
}

const fetchFormCategoriesList = async() => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/form/categories`);
        if (!response.ok) {
            throw new Error('Error retrieving categories');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
}

const updateRecipeData = async(recipeData) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/update`, {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(recipeData),
        })
        if (response.ok) {
            const updatedRecipe = await response.json();
            return updatedRecipe;
        } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

    } catch (error) {
        console.error('Update error:', error);
        throw error;
    }
    
}

const fetchGroceryList = async (recipeList) => {
    try {
      const response = await fetch(
        `${process.env.REACT_APP_BACKEND}/digitalkitchen/grocery/createGroceryList`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(recipeList),
        }
      );
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
  
      const data = await response.json();
  
      if (!data) {
        console.warn('Empty response');
      }
  
      return data;
    } catch (error) {
      console.error('Update error:', error);
      throw error;
    }
};

const postRecipe = async (recipe) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/createRecipe`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(recipe),
        });
        return response;
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
};

const postRecipeDuplicate = async (recipe) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_BACKEND}/digitalkitchen/recipes/createRecipe?bypass=true`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(recipe),
        });
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Error:', error);
        return null;
    }
};

  

const FetchManager = {
    fetchFormIngredientsList,
    fetchFormMeasurementsList,
    fetchFormTagsList,
    fetchFormAuthorsList,
    fetchFormCategoriesList,
    fetchGroceryList,
    updateRecipeData,
    postRecipe, 
    postRecipeDuplicate
};

export default FetchManager;