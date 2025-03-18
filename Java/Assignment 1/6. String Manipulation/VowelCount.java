// Implement a function to count the number of vowels in a string.
import java.util.Scanner;

public class VowelCount {
  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
          char ch = input.charAt(i);
          ch = Character.toLowerCase(ch);
          if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
              count++;
          }
        }
        System.out.println("Number of vowels: " + count);

        scanner.close();
    }
}
  
