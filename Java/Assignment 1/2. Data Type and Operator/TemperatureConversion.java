//Create a program to convert a temperature from Celsius to Fahrenheit and vice versa.
import java.util.Scanner;
public class TemperatureConversion {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Temperature Conversion:\n1. Celsius to Fahrenheit\n2. Fahrenheit to Celsius");
    System.out.print("Enter your choice (1 or 2): ");
    int x = scanner.nextInt();
    double celsius, fahrenheit;
    if (x == 1){
      System.out.print("Enter temperature in Celsius: ");
      celsius = scanner.nextDouble();
      fahrenheit = (celsius * 9 / 5) + 32;
      System.out.println("Temperature in Fahrenheit: " + fahrenheit);
            
    }
    else if (x == 2){
      System.out.print("Enter temperature in Fahrenheit: ");
      fahrenheit = scanner.nextDouble();
      celsius = (fahrenheit - 32) * 5 / 9;
      System.out.println("Temperature in Celsius: " + celsius);
    }
    else {
      System.out.println("Invalid choice!");
    }
    scanner.close();
  }
}
