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

const FetchManager = {
    fetchFormIngredientsList,
    fetchFormMeasurementsList,
    fetchFormTagsList,
    fetchFormAuthorsList,
    fetchFormCategoriesList
};

export default FetchManager;