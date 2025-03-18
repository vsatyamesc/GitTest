// Create a class to represent a student with attributes like name, roll number, and marks.
// Implement inheritance to create a "GraduateStudent" class that extends the "Student" class with additional features.

class Student {
    private String name;
    private int rollNumber;
    private int marks;

    public Student(String name, int rollNumber, int marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Marks: " + marks);
    }
}

class GraduateStudent extends Student {
    private String specialization;

    public GraduateStudent(String name, int rollNumber, int marks, String specialization) {
        super(name, rollNumber, marks);
        this.specialization = specialization;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
    }
}

public class OOP1 {
    public static void main(String[] args) {
        Student student = new Student("Alice", 101, 85);
        GraduateStudent graduateStudent = new GraduateStudent("Bob", 102, 90, "Computer Science");

        System.out.println("Student Info:");
        student.displayInfo();

        System.out.println("\nGraduate Student Info:");
        graduateStudent.displayInfo();
    }
}
