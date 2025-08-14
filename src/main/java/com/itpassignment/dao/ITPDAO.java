package com.itpassignment.dao;

import com.itpassignment.DatabaseConnection;
import com.itpassignment.model.ITP;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ITPDAO {

    public boolean insertITP(ITP itp) {
        String sql = "INSERT INTO ITP (itpName, region, enrollment) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itp.getItpName());
            pstmt.setString(2, itp.getRegion());
            pstmt.setInt(3, itp.getEnrollment());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting ITP: " + e.getMessage());
            return false;
        }
    }

    public List<ITP> getAllITPs() {
        List<ITP> itps = new ArrayList<>();
        String sql = "SELECT * FROM ITP";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ITP itp = new ITP(
                        rs.getString("itpName"),
                        rs.getString("region"),
                        rs.getInt("enrollment")
                );
                itps.add(itp);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving ITPs: " + e.getMessage());
        }

        return itps;
    }

    public boolean updateITP(ITP itp, String originalName) {
        String sql = "UPDATE ITP SET itpName = ?, region = ?, enrollment = ? WHERE itpName = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itp.getItpName());
            pstmt.setString(2, itp.getRegion());
            pstmt.setInt(3, itp.getEnrollment());
            pstmt.setString(4, originalName);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating ITP: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteITP(String itpName) {
        String sql = "DELETE FROM ITP WHERE itpName = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itpName);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting ITP: " + e.getMessage());
            return false;
        }
    }

    public List<ITP> getITPsByRegion(String region) {
        List<ITP> itps = new ArrayList<>();
        String sql = "SELECT * FROM ITP WHERE region = ? ORDER BY enrollment DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, region);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ITP itp = new ITP(
                        rs.getString("itpName"),
                        rs.getString("region"),
                        rs.getInt("enrollment")
                );
                itps.add(itp);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving ITPs by region: " + e.getMessage());
        }

        return itps;
    }
}