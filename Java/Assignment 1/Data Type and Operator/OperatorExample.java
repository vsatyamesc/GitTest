// Write a program to demonstrate the use of arithmetic, logical, and relational operators.

import java.util.Scanner;

public class OperatorExample {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        // Arithmetic Operators
        System.out.println("Enter two numbers for arithmetic operations:");
        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();

        System.out.println("Arithmetic Operations:");
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
        System.out.println(num1 + " % " + num2 + " = " + (num1 % num2));

        // Relational Operators
        System.out.println("\nRelational Operations:");
        System.out.println(num1 + " > " + num2 + " = " + (num1 > num2));
        System.out.println(num1 + " < " + num2 + " = " + (num1 < num2));
        System.out.println(num1 + " == " + num2 + " = " + (num1 == num2));
        System.out.println(num1 + " != " + num2 + " = " + (num1 != num2));

        // Logical Operators
        System.out.println("\nLogical Operations:");
        boolean condition1 = num1 > 0;
        boolean condition2 = num2 > 0;
        System.out.println("Condition1 (num1 > 0): " + condition1);
        System.out.println("Condition2 (num2 > 0): " + condition2);
        System.out.println("Condition1 AND Condition2: " + (condition1 && condition2));
        System.out.println("Condition1 OR Condition2: " + (condition1 || condition2));
        System.out.println("NOT Condition1: " + (!condition1));

        scanner.close();
  }
}
