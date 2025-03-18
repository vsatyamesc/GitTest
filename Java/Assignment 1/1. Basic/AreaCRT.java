// Write a program to calculate the area of a circle, rectangle, or triangle based on user input.
import java.util.Scanner;

public class AreaCRT {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Choose the shape to calculate the area:\n1. Circle\n2. Rectangle\n3. Triangle\nEnter your choice (1-3): ");
    int choice = scanner.nextInt();
    switch (choice) {
            case 1:
                System.out.print("Enter the radius of the circle: ");
                double radius = scanner.nextDouble();
                double circleArea = Math.PI * radius * radius;
                System.out.println("Area of the Circle: " + circleArea);
                break;
            case 2:
                System.out.print("Enter the length of the rectangle: ");
                double length = scanner.nextDouble();
                System.out.print("Enter the width of the rectangle: ");
                double width = scanner.nextDouble();
                double rectangleArea = length * width;
                System.out.println("Area of the Rectangle: " + rectangleArea);
                break;
            case 3:
                System.out.print("Enter the base of the triangle: ");
                double base = scanner.nextDouble();
                System.out.print("Enter the height of the triangle: ");
                double height = scanner.nextDouble();
                double triangleArea = 0.5 * base * height;
                System.out.println("Area of the Triangle: " + triangleArea);
                break;
            default:
                System.out.println("Invalid choice!");
        }
        scanner.close();
  }
}