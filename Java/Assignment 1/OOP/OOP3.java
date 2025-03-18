// Explain the concept of encapsulation with a suitable example.
class Person {
    private String firstName;
    private String lastName;

    //Getter
    public String getName() {
        return firstName + " " + lastName;
    }
        
    //Setter
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

public class OOP3 {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Satyam", "Verma"); 
        System.out.println("Name - " + p.getName());
    }
}