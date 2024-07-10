package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Bed")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedId;

    private String name;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "RoomId")
    private Room room;

    // Getters and setters
    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

// Getters and setters
