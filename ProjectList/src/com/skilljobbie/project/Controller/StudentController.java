package com.skilljobbie.project.Controller;

import com.skilljobbie.project.BO.StudentBO;
import com.skilljobbie.project.Service.StudentService;
import com.skilljobbie.project.Service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    public static StudentService studentService = new StudentServiceImpl();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. View Student By ID");
            System.out.println("5. Search Student By Name");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                updateStudent();
                break;
            case 4:
                viewStudentById();
                break;
            case 5:
                searchStudentByName();
                break;
            case 6:
                deleteStudent();
                break;
            case 7:
                System.out.println("Exit!");
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
        }
    }

    public static void addStudent() {
    	System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        StudentBO student = new StudentBO();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setEmail(email);
        student.setPhone(phone);
        student.setCourse(course);
        student.setAddress(address);

        studentService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    public static void viewAllStudents() {
        List<StudentBO> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n===== Student List =====");
        students.forEach(student -> 
            System.out.println(student.getId() + " - " + student.getName() + " - " + student.getAge()+ student.getEmail() + " - "+ student.getPhone() + " - "+ student.getCourse() + " - "+ student.getAddress())
        );
    }

    public static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        StudentBO student = studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
      
        System.out.print("Enter New Name (" + student.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Enter New Age (" + student.getAge() + "): ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Email (" + student.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.print("Enter New Phone Number (" + student.getPhone() + "): ");
        String phone = scanner.nextLine();
        System.out.print("Enter New Course (" + student.getCourse() + "): ");
        String course = scanner.nextLine();
        System.out.print("Enter New Address (" + student.getAddress() + "): ");
        String address = scanner.nextLine();

        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setEmail(email);
		student.setPhone(phone);
        student.setCourse(course);
        student.setAddress(address);

        studentService.updateStudent(student);
        System.out.println("Student updated successfully!");
    }

    public static void viewStudentById() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        StudentBO student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println("\n===== Student Details =====");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhone());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Address: " + student.getAddress());
        } else {
            System.out.println("Student not found!");
        }
    }

    public static void searchStudentByName() {
        System.out.print("Enter Student Name to Search: ");
        String name = scanner.nextLine();

        List<StudentBO> students = studentService.searchStudentsByName(name);
        if (students.isEmpty()) {
            System.out.println("No students found with name: " + name);
            return;
        }
        System.out.println("\n===== Search Results =====");
        students.forEach(student -> 
            System.out.println(student.getId() + " - " + student.getName() + " - " + student.getAge()+ student.getEmail() + " - "+ student.getPhone() + " - "+ student.getCourse() + " - "+ student.getAddress()));
    }

    public static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println(studentService.deleteStudent(id) ? "Student deleted successfully!" : "Student not found or could not be deleted.");
    }
}
