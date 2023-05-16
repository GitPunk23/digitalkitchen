import MultiStepForm from './components/forms/MultiStepForm';
import './styles/App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <h1>Create a new recipe</h1>
      <MultiStepForm />
    </div>
  );
}

export default App;
