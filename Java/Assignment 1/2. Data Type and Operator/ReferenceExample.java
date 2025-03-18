public class ReferenceExample {
    public static void main(String[] args) {
        String name = "Alice"; // Reference type for strings
        int[] marks = {85, 90, 78}; // Reference type for arrays

        System.out.println("Name: " + name);
        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }
}
  
//  Output: 
//  Name: Alice