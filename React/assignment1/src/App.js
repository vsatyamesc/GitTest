import "./App.css";
import UserCard from "./UserCard";

function App() {
  const details = [
    "Name: Alice",
    "Age: 22",
    "Job :SDE",
    "Address : 123 House, XYZ Colony, C.G. India",
  ];
  return (
    <div className="App">
      <h1>Assignment 1: Nested Functional Components with Props</h1>
      <UserCard details={details} />
    </div>
  );
}

export default App;
