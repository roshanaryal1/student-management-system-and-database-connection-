package com.itpassignment.dao;

import com.itpassignment.DatabaseConnection;
import com.itpassignment.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Create - Insert new student
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO Student (sID, sName, GPA, sizeHS) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getsID());
            pstmt.setString(2, student.getsName());
            pstmt.setDouble(3, student.getGPA());
            pstmt.setInt(4, student.getSizeHS());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            return false;
        }
    }

    // Read - Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("sID"),
                        rs.getString("sName"),
                        rs.getDouble("GPA"),
                        rs.getInt("sizeHS")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }

        return students;
    }

    // Update - Update existing student
    public boolean updateStudent(Student student) {
        String sql = "UPDATE Student SET sName = ?, GPA = ?, sizeHS = ? WHERE sID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getsName());
            pstmt.setDouble(2, student.getGPA());
            pstmt.setInt(3, student.getSizeHS());
            pstmt.setInt(4, student.getsID());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    // Delete - Delete student by ID
    public boolean deleteStudent(int sID) {
        String sql = "DELETE FROM Student WHERE sID = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sID);
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    // Custom operation - Get students by GPA range
    public List<Student> getStudentsByGPARange(double minGPA, double maxGPA) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student WHERE GPA BETWEEN ? AND ? ORDER BY GPA DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, minGPA);
            pstmt.setDouble(2, maxGPA);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("sID"),
                        rs.getString("sName"),
                        rs.getDouble("GPA"),
                        rs.getInt("sizeHS")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving students by GPA: " + e.getMessage());
        }

        return students;
    }
}