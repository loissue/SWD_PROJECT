package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private String email;
    private String password;
    private String userName;
    private String address;
    private int status;
    private int phoneNumber;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;

    // Getters and setters

    public Account(int accountId, String email, String password, String userName, String address, int status, int phoneNumber, Role role) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public Account() {

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

