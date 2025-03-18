// Explain the concept of interfaces and abstract classes with examples.
// Abstract class
abstract class Animal {
    // Abstract method (no body)
    public abstract void makeSound();

    // Concrete method (with body)
    public void sleep() {
        System.out.println("This animal is sleeping.");
    }
}

// Subclass of Animal
class Dog extends Animal {
    // Override the abstract method
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

public class AbstractClassExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound(); 
        dog.sleep();     
    }
}