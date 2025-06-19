import { useState } from "react";
export default function One() {
  const [counter, setCounter] = useState(0);
  const increment = () => {
    setCounter(counter + 1);
  };
  const decrement = () => {
    setCounter(counter - 1);
  };
  return (
    <div>
      <h2> 1. Counter With Increment and Decrement Button</h2>
      <div className="flex">
        <button onClick={() => increment()}>Increment</button>
        <button onClick={() => decrement()}>Decrement</button>
        <p>Value: {counter}</p>
      </div>
    </div>
  );
}
