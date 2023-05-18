import HomePage from './components/HomePage';
import CreateRecipePage from './components/CreateRecipePage';
import MultiStepForm from './components/forms/MultiStepForm';
import './styles/App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/create-recipe" element={<CreateRecipePage />} />
      </Routes>
    </Router>
  );
}

export default App;
//<MultiStepForm/>