// Demonstrate polymorphism by creating methods with the same name but different parameters in a parent and child class.
class Parent {
    public void show() {
        System.out.println("Parent class method");
    }

    public void show(String message) {
        System.out.println("Parent class overloaded method: " + message);
    }
}

class Child extends Parent {
    @Override
    public void show() {
        System.out.println("Child class overridden method");
    }

    public void show(int number) {
        System.out.println("Child class overloaded method: " + number);
    }
}

public class OOP2 {
    public static void main(String[] args) {
        Parent obj1 = new Parent();
        Parent obj2 = new Child();

        obj1.show();
        obj1.show("Hello");

        obj2.show();
        ((Child) obj2).show(42);
    }
}
