//Write a program to print the Fibonacci sequence up to a specified number.

import java.util.Scanner;

public class FibonacciSequence {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter to number to find the Fibonacci sequence upto :");
    int x = scanner.nextInt();
    String ss = "0 1";

    int i1 = 0;
    int i2 = 1;
    int temp = 1;
    if (x == 0) {
      System.out.println("The Fibonacci Sequence till " + x + " is");
      System.out.println(0);
      scanner.close();
      System.exit(0);
    }
    while (true) {
      temp = i1 + i2;
      if (temp > x) {
        break;
      }
      ss = ss + " " + temp;
      i1 = i2;
      i2 = temp;
    }
    System.out.println("The Fibonacci Sequence till " + x + " is");
    System.out.println(ss);
    scanner.close();
  }
}