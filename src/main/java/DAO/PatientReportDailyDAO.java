package DAO;

import Models.PatientDailyReport;
import Models.PatientRecord;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientReportDailyDAO {
    private SQLServerDataSource ds;

    public PatientReportDailyDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }

    public List<PatientDailyReport> getPatientReports() {
        List<PatientDailyReport> reports = new ArrayList<>();
        String query = "SELECT * FROM PatientDailyReport";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PatientDailyReport report = new PatientDailyReport();
                report.setReportId(rs.getInt("ReportId"));
                report.setSymtoms(rs.getString("Symtoms"));
                report.setRfe(rs.getString("RFE"));
                report.setTestAndResult(rs.getString("TestAndResult"));
                report.setTreatmentProgress(rs.getString("TreatmentProgress"));
                report.setDiagnose(rs.getString("Diagnose"));
                report.setTime(rs.getTimestamp("Time"));
                report.setNote(rs.getString("Note"));
                reports.add(report);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reports;
    }

    public void addPatientReport(PatientDailyReport report) {
        String query = "INSERT INTO PatientReportDaily (PatientId, RecordId, Symtoms, RFE, TestAndResult, TreatmentProgress, Diagnose, Time, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, report.getPatient().getPatientId());
            stmt.setInt(2, report.getPatientRecord().getRecordId());
            stmt.setString(3, report.getSymtoms());
            stmt.setString(4, report.getRfe());
            stmt.setString(5, report.getTestAndResult());
            stmt.setString(6, report.getTreatmentProgress());
            stmt.setString(7, report.getDiagnose());
            stmt.setTimestamp(8, new Timestamp(report.getTime().getTime()));
            stmt.setString(9, report.getNote());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PatientDailyReport> getReportsByRecordId(int recordId) {
        List<PatientDailyReport> reports = new ArrayList<>();
        String query = "SELECT * FROM PatientReportDaily WHERE RecordId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, recordId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PatientDailyReport report = new PatientDailyReport();
                    report.setReportId(rs.getInt("ReportId"));
                    report.setSymtoms(rs.getString("Symtoms"));
                    report.setRfe(rs.getString("RFE"));
                    report.setTestAndResult(rs.getString("TestAndResult"));
                    report.setTreatmentProgress(rs.getString("TreatmentProgress"));
                    report.setDiagnose(rs.getString("Diagnose"));
                    report.setTime(rs.getTimestamp("Time"));
                    report.setNote(rs.getString("Note"));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reports;
    }
    public List<PatientDailyReport> getDailyReportsByRecordId(int recordId) {
        List<PatientDailyReport> reports = new ArrayList<>();
        String query = "SELECT * FROM PatientReportDaily WHERE RecordId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PatientDailyReport report = new PatientDailyReport();
                report.setReportId(rs.getInt("ReportId"));
                report.setSymtoms(rs.getString("Symtoms"));
                report.setRfe(rs.getString("RFE"));
                report.setTestAndResult(rs.getString("TestAndResult"));
                report.setTreatmentProgress(rs.getString("TreatmentProgress"));
                report.setDiagnose(rs.getString("Diagnose"));
                report.setTime(rs.getTimestamp("Time"));
                report.setNote(rs.getString("Note"));
                reports.add(report);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reports;
    }

    public void deleteDailyReport(int reportId) {
        String query = "DELETE FROM PatientReportDaily WHERE ReportId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, reportId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PatientDailyReport getDailyReportById(int reportId) {
        String query = "SELECT * FROM PatientReportDaily WHERE ReportId = ?";
        PatientDailyReport report = null;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                report = new PatientDailyReport();
                report.setReportId(rs.getInt("ReportId"));
                report.setSymtoms(rs.getString("Symtoms"));
                report.setRfe(rs.getString("RFE"));
                report.setTestAndResult(rs.getString("TestAndResult"));
                report.setTreatmentProgress(rs.getString("TreatmentProgress"));
                report.setDiagnose(rs.getString("Diagnose"));
                report.setTime(rs.getTimestamp("Time"));
                report.setNote(rs.getString("Note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return report;
    }

    public void updateDailyReport(PatientDailyReport report) {
        String query = "UPDATE PatientReportDaily SET Symtoms = ?, RFE = ?, TestAndResult = ?, TreatmentProgress = ?, Diagnose = ?, Note = ? WHERE ReportId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, report.getSymtoms());
            stmt.setString(2, report.getRfe());
            stmt.setString(3, report.getTestAndResult());
            stmt.setString(4, report.getTreatmentProgress());
            stmt.setString(5, report.getDiagnose());
            stmt.setString(6, report.getNote());
            stmt.setInt(7, report.getReportId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        PatientReportDailyDAO  patientrDAO = new PatientReportDailyDAO();
        List<PatientDailyReport> patients = patientrDAO.getReportsByRecordId(7);

        System.out.println(patients);
    }
}

