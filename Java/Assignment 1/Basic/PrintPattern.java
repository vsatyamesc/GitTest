// Use loops to print patterns like a triangle or square.
import java.util.Scanner;

public class PrintPattern {
    
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What do you want to print?\n1.Square\n2.Triangle");
            int x = scanner.nextInt();
            System.err.println("Enter the Number of Rows to Print: ");
            int y = scanner.nextInt();
            if (x == 1) {
                printSquare(y);
            }
            else if (x == 2) {
                printTriangle(y);
            } else {
                System.err.println("You've Entered a Wrong Input");
            }
            scanner.close();
    }

    public static void printTriangle(int x) {
        for (int i = 1; i <= x; i++) {
            for (int j = x; j > i; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }
    
    public static void printSquare(int x) {
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }
}
