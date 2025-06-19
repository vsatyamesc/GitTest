import logo from "./logo.svg";
import UserDetails from "./UserDetails";
export default function UserCard(props) {
  return (
    <div className="UserProfile">
      <img src={logo} alt="logo" className="AppLogo"></img>
      <UserDetails details={props.details} />
    </div>
  );
}
