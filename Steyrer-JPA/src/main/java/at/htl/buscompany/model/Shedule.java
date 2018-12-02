package at.htl.buscompany.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Bus bus;
    @ManyToOne
    private BusStop busStop;
    private LocalDateTime stopTime;

    //region Constructor
    public Shedule() {
    }

    public Shedule(Bus bus, BusStop busStop, LocalDateTime stopTime) {
        this.bus = bus;
        this.busStop = busStop;
        this.stopTime = stopTime;
    }

    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    //endregion
}
