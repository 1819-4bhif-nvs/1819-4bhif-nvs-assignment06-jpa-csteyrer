package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "tickets")
    private List<Bus> buses;

    @ManyToOne
    private Busstop busStop;

    @Column(name = "DTYPE", updatable = false, insertable = false)
    private String dtype;

    private LocalDateTime buyingTime;
    private double price;


    public Ticket()
    {

    }
    public Ticket(Busstop busStop, LocalDateTime buyingTime, double price) {
        this.buses = new LinkedList<>();
        this.busStop = busStop;
        this.buyingTime = buyingTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBus(Bus bus) {
        buses.add(bus);
        bus.setTicket(this);
    }

    public Busstop getBusStop() {
        return busStop;
    }

    public void setBusStop(Busstop busStop) {
        this.busStop = busStop;
    }

    public LocalDateTime getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(LocalDateTime buyingTime) {
        this.buyingTime = buyingTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
