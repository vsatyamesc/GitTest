// Implement a program to find the factorial of a given number.

import java.util.Scanner;

public class FactorialNum {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a number to find Factorial: ");
    int x = scanner.nextInt();
    int answer = 1;
    for (int i = 1; i <= x; i++) {
      answer *= i;
    }
    System.out.println("The Factorial of " + x + " is " + answer);
    scanner.close();
  }
}
