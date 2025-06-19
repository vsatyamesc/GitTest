import "../App.css";
import { useState } from "react";
export default function Two() {
  const [text, setText] = useState("");
  return (
    <div>
      <h2>2. An Input field that displays the typed text live below it</h2>
      <div className="flex-col">
        <input type="text" onChange={(e) => setText(e.target.value)} />
        <h2>Value: {text}</h2>
      </div>
    </div>
  );
}
