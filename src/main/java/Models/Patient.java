package Models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    private String email;
    private String address;
    private String name;
    private String phone;
    private String emergency;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "bedId")
    private Bed bed;

    public Patient(int patientId) {
        this.patientId = patientId;
    }

    public Patient() {

    }

    // Getters and setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Patient(int patientId, String address, String email, String name, String emergency, boolean status, Bed bed, String phone) {
        this.patientId = patientId;
        this.address = address;
        this.email = email;
        this.name = name;
        this.emergency = emergency;
        this.status = status;
        this.bed = bed;
        this.phone = phone;
    }
}

