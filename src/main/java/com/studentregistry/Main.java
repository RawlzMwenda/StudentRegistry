package com.studentregistry;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentRepository<Student> repository =
            new StudentRepository<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== STUDENT REGISTRY =====");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Quit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> listStudents();
                    case 3 -> searchStudent();
                    case 4 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid menu choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter GPA (0.0 - 4.0): ");
            double gpa = Double.parseDouble(scanner.nextLine());

            System.out.println("1. Undergraduate");
            System.out.println("2. Postgraduate");
            System.out.print("Enter student type: ");
            int type = Integer.parseInt(scanner.nextLine());

            Student student;
            switch (type) {
                case 1 -> student = new Student(nextId, name, gpa);
                case 2 -> student = new PostgraduateStudent(nextId, name, gpa);
                default -> throw new IllegalArgumentException("Invalid student type.");
            }

            repository.add(student);
            nextId++;
            System.out.println("Student added successfully!");
            System.out.println(student);
        } catch (NumberFormatException e) {
            System.out.println("Error: Enter valid numeric values.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listStudents() {
        System.out.println("\n===== ALL STUDENTS =====");
        if (repository.findAll().isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        repository.findAll().stream().forEach(System.out::println);
    }

    private static void searchStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Student> result = repository.findById(id);
            result.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Student not found.")
            );
        } catch (NumberFormatException e) {
            System.out.println("Error: Student ID must be a number.");
        }
    }
}
