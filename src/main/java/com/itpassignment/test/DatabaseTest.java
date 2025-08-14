package com.itpassignment.test;

import com.itpassignment.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            System.out.println("✓ Database connected successfully!");

            // Test query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM Student");

            if (rs.next()) {
                System.out.println("✓ Found " + rs.getInt("count") + " students in database");
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}