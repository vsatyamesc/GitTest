//Create a program to check if a number is even or odd.
import java.util.Scanner;
public class EvenOdd {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the Number to Check for Even or Odd:");
    int x = scanner.nextInt();

    if (x % 2 == 0) {
      System.out.println(String.format("The Number %d is Even.", x));
    } else {
      System.out.println(String.format("The Number %d is Odd.", x));
    }
    scanner.close();
  }
}
