package com.itpassignment.dao;

import com.itpassignment.DatabaseConnection;
import com.itpassignment.model.Apply;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplyDAO {

    public boolean insertApplication(Apply apply) {
        String sql = "INSERT INTO Apply (sID, itpName, major, decision) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, apply.getSID());
            pstmt.setString(2, apply.getItpName());
            pstmt.setString(3, apply.getMajor());
            pstmt.setString(4, apply.getDecision());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting application: " + e.getMessage());
            return false;
        }
    }

    public List<Apply> getAllApplications() {
        List<Apply> applications = new ArrayList<>();
        String sql = "SELECT * FROM Apply";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Apply apply = new Apply(
                        rs.getInt("sID"),
                        rs.getString("itpName"),
                        rs.getString("major"),
                        rs.getString("decision")
                );
                applications.add(apply);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving applications: " + e.getMessage());
        }

        return applications;
    }

    public boolean updateApplication(Apply apply, int originalSID, String originalITP, String originalMajor) {
        String sql = "UPDATE Apply SET sID = ?, itpName = ?, major = ?, decision = ? WHERE sID = ? AND itpName = ? AND major = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, apply.getSID());
            pstmt.setString(2, apply.getItpName());
            pstmt.setString(3, apply.getMajor());
            pstmt.setString(4, apply.getDecision());
            pstmt.setInt(5, originalSID);
            pstmt.setString(6, originalITP);
            pstmt.setString(7, originalMajor);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating application: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteApplication(int sID, String itpName, String major) {
        String sql = "DELETE FROM Apply WHERE sID = ? AND itpName = ? AND major = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sID);
            pstmt.setString(2, itpName);
            pstmt.setString(3, major);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting application: " + e.getMessage());
            return false;
        }
    }

    public List<Apply> getAcceptedApplications() {
        List<Apply> applications = new ArrayList<>();
        String sql = "SELECT * FROM Apply WHERE decision = 'Y' ORDER BY sID";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Apply apply = new Apply(
                        rs.getInt("sID"),
                        rs.getString("itpName"),
                        rs.getString("major"),
                        rs.getString("decision")
                );
                applications.add(apply);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving accepted applications: " + e.getMessage());
        }

        return applications;
    }
}