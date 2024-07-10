package DAO;

import Models.Account;
import Models.Role;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private SQLServerDataSource ds;

    public AccountDAO() {
        ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("LAPTOP-QBNHB0EN\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("SWD");
        ds.setTrustServerCertificate(true);
    }

    public Account login(String email, String password) {
        String query = "SELECT a.*, r.role, r.id AS roleId FROM Account a " +
                "JOIN Role r ON a.roleID = r.id " +
                "WHERE a.email = ? AND a.password = ?";
        Account account = null;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("roleId"));
                    role.setRole(rs.getString("role"));

                    account = new Account();
                    account.setAccountId(rs.getInt("AccountId"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setUserName(rs.getString("userName"));
                    account.setAddress(rs.getString("address"));
                    account.setStatus(rs.getInt("status"));
                    account.setPhoneNumber(rs.getInt("phoneNumber"));
                    account.setRole(role);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }
    public static void main(String[] args) {
        AccountDAO adao = new AccountDAO();
        Account  a = adao.login("noneednow","121212");
        System.out.println(a.getAccountId());
    }
}