package DAO;

import Models.Room;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private SQLServerDataSource ds;

    public RoomDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setType(rs.getString("Type"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rooms;
    }

    public Room getRoomById(int id) {
        Room room = null;
        String query = "SELECT * FROM Room WHERE Id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setType(rs.getString("Type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return room;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO Room (Name, Type) VALUES (?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRoom(Room room) {
        String query = "UPDATE Room SET Name = ?, Type = ? WHERE Id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getType());
            stmt.setInt(3, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM Room WHERE Id = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
