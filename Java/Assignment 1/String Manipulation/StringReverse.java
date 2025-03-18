//Write a program to reverse a given string.

import java.util.Scanner;

public class StringReverse {
  public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        System.out.println("Reversed String: " + reversed);

        scanner.close();
    }
}
