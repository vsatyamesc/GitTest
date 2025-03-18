// Explain the concept of interfaces and abstract classes with examples.
// Interface
interface Animal {
    // Abstract method (no body)
    void makeSound();

    // Default method (with body)
    void sleep();
}

// Class implementing the interface
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    @Override
    public void sleep() {
        System.out.println("Zzz");
    }
}
public class InterfaceClassExample {
  public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound(); 
        dog.sleep();     
    }
}
