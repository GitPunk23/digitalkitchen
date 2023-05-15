import logo from './logo.svg';
import './App.css';
import RecipeForm from './RecipeForm';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

function WelcomePage() {
  return (
    <div>
      <h1>Create a new recipe</h1>
      <RecipeForm />
    </div>
  );
}

export default WelcomePage;
