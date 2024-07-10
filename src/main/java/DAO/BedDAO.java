package DAO;

import Models.Bed;
import Models.Room;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BedDAO {
    private SQLServerDataSource ds;

    public BedDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }

    public List<Bed> getBedsByRoomId(int roomId) {
        List<Bed> beds = new ArrayList<>();
        String query = "SELECT * FROM Bed WHERE RoomId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getInt("BedId"));
                bed.setName(rs.getString("Name"));
                bed.setStatus(rs.getBoolean("Status"));

                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                bed.setRoom(room);

                beds.add(bed);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return beds;
    }

    public Bed getBedById(int bedId) {
        Bed bed = null;
        String query = "SELECT * FROM Bed WHERE BedId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, bedId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bed = new Bed();
                bed.setBedId(rs.getInt("BedId"));
                bed.setName(rs.getString("Name"));
                bed.setStatus(rs.getBoolean("Status"));

                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                bed.setRoom(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bed;
    }

    public void addBed(Bed bed) {
        String query = "INSERT INTO Bed (Name, Status, RoomId) VALUES (?, ?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, bed.getName());
            stmt.setBoolean(2, bed.isStatus());
            stmt.setInt(3, bed.getRoom().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBed(Bed bed) {
        String query = "UPDATE Bed SET Name = ?, Status = ?, RoomId = ? WHERE BedId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, bed.getName());
            stmt.setBoolean(2, bed.isStatus());
            stmt.setInt(3, bed.getRoom().getId());
            stmt.setInt(4, bed.getBedId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBed(int bedId) {
        String query = "DELETE FROM Bed WHERE BedId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, bedId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void allocatePatientToBed(int bedId) {
        String query = "UPDATE Bed SET Status = 1 WHERE BedId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, bedId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dischargePatientFromBed(int bedId) {
        String query = "UPDATE Bed SET Status = 0 WHERE BedId = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, bedId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
