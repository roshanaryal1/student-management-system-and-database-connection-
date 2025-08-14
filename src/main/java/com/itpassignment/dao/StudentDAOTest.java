package com.itpassignment.test;

import com.itpassignment.dao.StudentDAO;
import com.itpassignment.model.Student;
import java.util.List;

public class StudentDAOTest {
    public static void main(String[] args) {
        System.out.println("=== Testing StudentDAO ===");

        StudentDAO studentDAO = new StudentDAO();

        // Test: Get all students
        System.out.println("\n1. Testing getAllStudents():");
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Total students: " + students.size());

        // Test: GPA range search
        System.out.println("\n2. Testing GPA range search (3.5 - 4.0):");
        List<Student> highGPAStudents = studentDAO.getStudentsByGPARange(3.5, 4.0);
        for (Student student : highGPAStudents) {
            System.out.println(student);
        }

        // Test: Insert new student
        System.out.println("\n3. Testing insert new student:");
        Student newStudent = new Student(999, "TestStudent", 3.7, 1200);
        if (studentDAO.insertStudent(newStudent)) {
            System.out.println("✓ Student inserted successfully");
        } else {
            System.out.println("❌ Failed to insert student");
        }

        // Test: Delete the test student
        System.out.println("\n4. Testing delete student:");
        if (studentDAO.deleteStudent(999)) {
            System.out.println("✓ Student deleted successfully");
        } else {
            System.out.println("❌ Failed to delete student");
        }

        System.out.println("\n=== All tests completed! ===");
    }
}