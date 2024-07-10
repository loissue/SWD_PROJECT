package Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String service;
    private float price;

    @OneToMany(mappedBy = "service")
    private List<ServiceReceipt> serviceReceipts;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ServiceReceipt> getServiceReceipts() {
        return serviceReceipts;
    }

    public void setServiceReceipts(List<ServiceReceipt> serviceReceipts) {
        this.serviceReceipts = serviceReceipts;
    }
}

