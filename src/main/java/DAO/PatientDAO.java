package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import Models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PatientDAO {
    private SQLServerDataSource ds;

    public PatientDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }

    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("PatientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setEmergency(rs.getString("emergency"));
                patient.setStatus(rs.getBoolean("status"));
                patient.setPhone(rs.getString("phone"));
                patient.setEmail(rs.getString("email"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }

    public void addPatient(Patient patient) {
        String query = "INSERT INTO Patient (Name, Phone, Address, Emergency, Status, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getPhone());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getEmergency());
            stmt.setBoolean(5, patient.isStatus());
            stmt.setString(6, patient.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patient> searchPatients(String keyword) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient WHERE name LIKE ? OR phone LIKE ? OR email LIKE ? OR address LIKE ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("PatientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setEmergency(rs.getString("emergency"));
                patient.setStatus(rs.getBoolean("status"));
                patient.setPhone(rs.getString("phone"));
                patient.setEmail(rs.getString("email"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<Patient> sortPatients(List<Patient> patients, String sortBy, String order) {
        Comparator<Patient> comparator;

        switch (sortBy) {
            case "id":
                comparator = Comparator.comparingInt(Patient::getPatientId);
                break;
            case "name":
                comparator = Comparator.comparing(Patient::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "phone":
                comparator = Comparator.comparing(Patient::getPhone);
                break;
            case "email":
                comparator = Comparator.comparing(Patient::getEmail, String.CASE_INSENSITIVE_ORDER);
                break;
            case "address":
                comparator = Comparator.comparing(Patient::getAddress, String.CASE_INSENSITIVE_ORDER);
                break;
            case "emergency":
                comparator = Comparator.comparing(Patient::getEmergency, String.CASE_INSENSITIVE_ORDER);
                break;
            case "status":
                comparator = Comparator.comparing(Patient::isStatus);
                break;
            default:
                comparator = Comparator.comparingInt(Patient::getPatientId);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        patients.sort(comparator);
        return patients;
    }

    public Patient getPatientById(int id) {
        String query = "SELECT * FROM Patient WHERE PatientId = ?";
        Patient patient = null;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient();
                    patient.setPatientId(rs.getInt("PatientId"));
                    patient.setName(rs.getString("name"));
                    patient.setAddress(rs.getString("address"));
                    patient.setEmergency(rs.getString("emergency"));
                    patient.setStatus(rs.getBoolean("status"));
                    patient.setPhone(rs.getString("phone"));
                    patient.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patient;
    }

    public void updatePatient(Patient patient) {
        String query = "UPDATE Patient SET Name = ?, Phone = ?, Address = ?, Emergency = ?, Status = ?, Email = ? WHERE PatientId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getPhone());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getEmergency());
            stmt.setBoolean(5, patient.isStatus());
            stmt.setString(6, patient.getEmail());
            stmt.setInt(7, patient.getPatientId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePatient(int id) {
        String query = "DELETE FROM Patient WHERE PatientId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
