package Database;

import ModelClasses.Criminal;
import ModelClasses.Fir;
import ModelClasses.Officer;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/Jail_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Mapple28!";

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add officer
    public void AddInDBOfficer(Officer officer) {
        String insertSQL = "INSERT INTO Officers (officer_id, officer_name, officer_rank, onduty) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, officer.getId());
            pstmt.setString(2, officer.getName());
            pstmt.setString(3, officer.getRank());
            pstmt.setBoolean(4, officer.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Officer inserted to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add criminal
    public void AddInDBCriminal(Criminal criminal) {
        String insertSQL = "INSERT INTO Criminal (criminal_id, criminal_name, criminal_age, type_of_criminal, status_of_criminal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, criminal.getId());
            pstmt.setString(2, criminal.getName());
            pstmt.setInt(3, criminal.getAge());
            pstmt.setString(4, criminal.getCrimeType());
            pstmt.setString(5, "At very large");  // default status
            pstmt.executeUpdate();
            System.out.println("Criminal inserted to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add FIR
    public void AddInDBFir(Fir fir) {
        String insertSQL = "INSERT INTO FIRs (fir_no, description, time, location, status_fir) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, fir.getFirNumber());
            pstmt.setString(2, fir.getDescription());
            pstmt.setTimestamp(3, Timestamp.valueOf(fir.getDateTime())); // Convert LocalDateTime to Timestamp
            pstmt.setString(4, fir.getLocation());
            pstmt.setString(5, fir.getStatus());
            pstmt.executeUpdate();
            System.out.println("FIR inserted to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete officer
    public void DeleteOfficer(int id) {
        String deleteSQL = "DELETE FROM Officers WHERE officer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Officer deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete criminal
    public void DeleteCriminal(String id) {
        String deleteSQL = "DELETE FROM Criminal WHERE criminal_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            System.out.println("Criminal deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete FIR
    public void DeleteFir(String firNumber) {
        String deleteSQL = "DELETE FROM FIRs WHERE fir_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, firNumber);
            pstmt.executeUpdate();
            System.out.println("FIR deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update criminal status
    public void UpdateInCriminal(String id, String newStatus) {
        String updateSQL = "UPDATE Criminal SET status_of_criminal = ? WHERE criminal_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            System.out.println("Criminal status updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update FIR description and location
    public void UpdateInFir(String firNumber, String newDescription, String newLocation) {
        String updateSQL = "UPDATE FIRs SET location = ?, description = ? WHERE fir_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, newLocation);
            pstmt.setString(2, newDescription);
            pstmt.setString(3, firNumber);
            pstmt.executeUpdate();
            System.out.println("FIR location and description updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
