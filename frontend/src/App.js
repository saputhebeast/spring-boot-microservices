import './App.css';
import EmployeeComponent from './components/EmployeeComponent';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <div className="container"><h1>Employee Microservices</h1></div>
        <EmployeeComponent />
      </header>
    </div>
  );
}

export default App;
