import React, { createContext, useContext, useState, useEffect } from 'react';
import FetchManager from '../components/util/FetchManager';

const MeasurementsContext = createContext();

export function useMeasurementsList() {
  return useContext(MeasurementsContext);
}

export function MeasurementsProvider({ children }) {
    const [measurementsList, setMeasurementsList] = useState([]);

    useEffect(() => {
        FetchManager.fetchFormMeasurementsList()
        .then((data) => {
            const measurements = Object.values(data).map((item) => item.measurement);
            setMeasurementsList(measurements);
        })
        .catch((error) => {
            console.log(error);
        });
    }, []);

    return (
        <MeasurementsContext.Provider value={{ measurementsList, setMeasurementsList }}>
            {children}
        </MeasurementsContext.Provider>
    );
}
