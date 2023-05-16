import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RecipeForm from './RecipeForm';
import IngredientForm from './IngredientForm';

function WelcomePage() {
  return (
    <div>
      <h1>Create a new recipe</h1>
      <RecipeForm />
    </div>
  );
}

function App() {
  return (
    <Router>
      <div className='App'>
        <Routes>
          <Route exact path="/" element={<RecipeForm />} />
          <Route path="/ingredients" element={<IngredientForm />} />
        </Routes>
      </div>
    </Router>
  );
}


export default App;
