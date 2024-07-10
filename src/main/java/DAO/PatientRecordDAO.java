package DAO;

import Models.Patient;
import Models.PatientRecord;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRecordDAO {
    private SQLServerDataSource ds;

    public PatientRecordDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }
    public PatientRecord getPatientRecordById(int recordId) {
        String query = "SELECT * FROM PatientRecord WHERE RecordId = ?";
        PatientRecord record = null;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                record = new PatientRecord();
                record.setRecordId(rs.getInt("RecordId"));
                record.setFamilyHistory(rs.getString("FamilyHistory"));
                record.setAllergies(rs.getString("Allergies"));
                record.setSurgicalProcedures(rs.getString("SurgicalProcedures"));
                record.setCondition(rs.getString("Condition"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return record;
    }
    public List<PatientRecord> getPatientRecords() {
        List<PatientRecord> records = new ArrayList<>();
        String query = "SELECT * FROM PatientRecord";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PatientRecord record = new PatientRecord();
                record.setRecordId(rs.getInt("RecordId"));
                record.setFamilyHistory(rs.getString("FamilyHistory"));
                record.setAllergies(rs.getString("Allergies"));
                record.setSurgicalProcedures(rs.getString("SurgicalProcedures"));
                record.setCondition(rs.getString("Condition"));
                records.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return records;
    }
    public void deleteRecord(int recordId) {
        String query = "DELETE FROM PatientReportDaily WHERE RecordId = ? DELETE FROM PatientRecord WHERE RecordId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, recordId);
            stmt.setInt(2, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editRecord(PatientRecord record) {
        String query = "UPDATE PatientRecord SET FamilyHistory = ?, Allergies = ?, SurgicalProcedures = ?, [Condition] = ? WHERE RecordId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, record.getFamilyHistory());
            stmt.setString(2, record.getAllergies());
            stmt.setString(3, record.getSurgicalProcedures());
            stmt.setString(4, record.getCondition());
            stmt.setInt(5, record.getRecordId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addPatientRecord(PatientRecord record) {
        String query = "INSERT INTO PatientRecord (FamilyHistory, Allergies, SurgicalProcedures, Condition, patientId, accountId) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, record.getFamilyHistory());
            stmt.setString(2, record.getAllergies());
            stmt.setString(3, record.getSurgicalProcedures());
            stmt.setString(4, record.getCondition());
            stmt.setInt(5, record.getPatient().getPatientId());
            stmt.setInt(6, record.getAccountId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<PatientRecord> getRecordsByPatientId(int patientId) {
        List<PatientRecord> records = new ArrayList<>();
        String query = "SELECT * FROM PatientRecord WHERE patientId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PatientRecord record = new PatientRecord();
                    record.setRecordId(rs.getInt("RecordId"));
                    record.setFamilyHistory(rs.getString("FamilyHistory"));
                    record.setAllergies(rs.getString("Allergies"));
                    record.setSurgicalProcedures(rs.getString("SurgicalProcedures"));
                    record.setCondition(rs.getString("Condition"));
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return records;
    }
    public List<PatientRecord> getPatientRecordsByPatientId(int patientId) {
        List<PatientRecord> records = new ArrayList<>();
        String query = "SELECT * FROM PatientRecord WHERE PatientId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PatientRecord record = new PatientRecord();
                record.setRecordId(rs.getInt("RecordId"));
                record.setFamilyHistory(rs.getString("FamilyHistory"));
                record.setAllergies(rs.getString("Allergies"));
                record.setSurgicalProcedures(rs.getString("SurgicalProcedures"));
                record.setCondition(rs.getString("Condition"));
                records.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return records;
    }
    public static void main(String[] args) {
        PatientRecordDAO  patientrDAO = new PatientRecordDAO();
        PatientRecord patients = patientrDAO.getPatientRecordById(7);

        System.out.println(patients.getAllergies());
    }
}


