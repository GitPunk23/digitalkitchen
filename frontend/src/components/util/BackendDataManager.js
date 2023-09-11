import React, { useState } from 'react';

const BackendDataManager = ({ measurementsList, authorsList, categoriesList}) => {
    const [measurements, setMeasurements] = useState(measurementsList);
    const [authors, setAuthors] = useState(authorsList);
    const [categories, setCategories] = useState(categoriesList)

}

export default BackendDataManager;