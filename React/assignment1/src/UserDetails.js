export default function UserDetails({ details }) {
  console.log(details);
  return (
    <div>
      {details.map((item, index) => (
        <p key={index}>{item}</p>
      ))}
    </div>
  );
}
