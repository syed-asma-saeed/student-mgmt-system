package com.sms;

import com.sms.enums.Department;
import com.sms.exceptions.DuplicateStudentException;
import com.sms.exceptions.StudentNotFoundException;
import com.sms.services.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        StudentService sms = new StudentService();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- STUDENT SYSTEM OPTIONS ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search By Name");
            System.out.println("5. Search By Department");
            System.out.println("6. View All By Name");
            System.out.println("7. View All By Marks");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1: {
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();

                        System.out.print("Enter Marks: ");
                        double marks = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Departments:");
                        int cnt = 1;
                        for (Department.dept d : Department.dept.values()) {
                            System.out.println(cnt++ + "." + d);
                        }

                        System.out.print("Enter the number for the chosen Department: ");
                        int deptChoice = scanner.nextInt();
                        scanner.nextLine();

                        Department.dept[] departments = Department.dept.values();
                        if (deptChoice < 1 || deptChoice > departments.length) {
                            System.out.println("Invalid Department.");
                            break;
                        }

                        Department.dept selectedDept = departments[deptChoice - 1];

                        String id = sms.addStudent(name, age, marks, selectedDept);
                        System.out.println("Student added successfully.");
                        System.out.println("Student ID: " + id);
                    }
                    break;


                    case 2: {
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();

                        System.out.print("Enter the Name to be Updated: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter the Marks to be Updated: ");
                        double marks = scanner.nextDouble();
                        scanner.nextLine();

                        sms.updateStudent(studentId, name, marks);
                        System.out.println("Student deleted successfully.");
                    }
                    break;

                    case 3: {
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();

                        sms.deleteStudent(studentId);
                        System.out.println("Student deleted successfully.");
                    }
                    break;

                    case 4: {
                        System.out.print("Enter Name: ");
                        String searchName = scanner.nextLine();

                        try{
                            sms.searchByName(searchName).forEach(System.out::println);
                        }
                        catch (InputMismatchException e)  {
                            throw new StudentNotFoundException("No Student found with the given Name.");
                        }
                    }
                    break;

                    case 5:{
                        System.out.println("Departments:");
                        int cnt = 1;
                        for (Department.dept d : Department.dept.values()) {
                            System.out.println(cnt++ +". " + d);
                        }

                        System.out.print("Enter the chosen Department: ");
                        int deptChoice = scanner.nextInt();
                        scanner.nextLine();

                        Department.dept[] departments = Department.dept.values();
                        if (deptChoice < 1 || deptChoice > departments.length) {
                            System.out.println("Invalid Department.");
                            break;
                        }

                        Department.dept selectedDept = departments[deptChoice - 1];

                        sms.searchByDepartment(selectedDept)
                                .forEach(System.out::println);
                    }
                    break;

                    case 6:
                        sms.getAllSortedByName().forEach(System.out::println);
                    break;

                    case 7:
                        sms.getAllSortedByMarks().forEach(System.out::println);
                    break;

                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                    return;

                    default: System.out.println("Invalid choice.");
                }


            } catch (StudentNotFoundException |
                     DuplicateStudentException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}