import logo from './logo.svg';
import './App.css';

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
      <h1>Welcome to My App!</h1>
      <p>Thank you for choosing our app. We hope you find it useful.</p>
    </div>
  );
}

function FirstRecord() {
  const [record, setRecord] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/digitalkitchen/recipes/get')
      .then(response => response.json())
      .then(data => setRecord(data[0]));
  }, []);

  return (
    <div>
      {record ? (
        <div>
          <h1>{record.title}</h1>
          <p>{record.description}</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}


export default WelcomePage;
