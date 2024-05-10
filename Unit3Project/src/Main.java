import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Record Management System");
            System.out.println("1. Add new student");
            System.out.println("2. Update student information");
            System.out.println("3. View student details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    viewStudentDetails(scanner);
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addNewStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();

        Student student = new Student(name, id, age, grade);
        StudentManagement.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter student ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student existingStudent = StudentManagement.findStudent(updateId);
        if (existingStudent == null) {
            System.out.println("Student with ID " + updateId + " not found.");
            return;
        }

        System.out.print("Enter updated student name: ");
        String updatedName = scanner.nextLine();
        System.out.print("Enter updated student age: ");
        int updatedAge = scanner.nextInt();
        System.out.print("Enter updated student grade: ");
        double updatedGrade = scanner.nextDouble();

        Student updatedStudent = new Student(updatedName, updateId, updatedAge, updatedGrade);
        StudentManagement.updateStudent(updateId, updatedStudent);
        System.out.println("Student information updated successfully.");
    }

    private static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter student ID to view details: ");
        int viewId = scanner.nextInt();

        Student student = StudentManagement.findStudent(viewId);
        if (student == null) {
            System.out.println("Student with ID " + viewId + " not found.");
        } else {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Grade: " + student.getGrade());
        }
    }
}

class Student {
    private String name;
    private int id;
    private int age;
    private double grade;

    // Constructor
    public Student(String name, int id, int age, double grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

class StudentManagement {
    private static int totalStudents = 0;
    private static List<Student> studentList = new ArrayList<>();

    // Method to add a new student
    public static void addStudent(Student student) {
        studentList.add(student);
        totalStudents++;
    }

    // Method to update student information
    public static void updateStudent(int id, Student updatedStudent) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setGrade(updatedStudent.getGrade());
                break;
            }
        }
    }

    // Method to view student details
    public static Student findStudent(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
