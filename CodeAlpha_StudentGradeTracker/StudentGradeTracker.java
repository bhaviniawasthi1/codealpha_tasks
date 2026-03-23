import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

// Main Class
public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== STUDENT GRADE TRACKER =====");

        // Input number of students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Grade: ");
            double grade = sc.nextDouble();
            sc.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Calculate stats
        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        for (Student s : students) {
            double g = s.getGrade();
            total += g;

            if (g > highest) highest = g;
            if (g < lowest) lowest = g;
        }

        double average = total / students.size();

        // Display Report
        System.out.println("\n===== STUDENT REPORT =====");

        for (Student s : students) {
            System.out.println("Name: " + s.getName() + " | Grade: " + s.getGrade());
        }

        System.out.println("\n===== STATISTICS =====");
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);

        System.out.println("\nProgram Completed Successfully!");

        sc.close();
    }
}