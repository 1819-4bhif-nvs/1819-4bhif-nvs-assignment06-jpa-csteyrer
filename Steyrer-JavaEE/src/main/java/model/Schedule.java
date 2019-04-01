package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "busstop_id")
    private Busstop busStop;

    private LocalDateTime stopTime;

    public Schedule() {
    }

    public Schedule(Bus bus, Busstop busStop, LocalDateTime stopTime) {
        this.bus = bus;
        this.bus.setSchedule(this);
        this.busStop = busStop;
        this.busStop.setSchedule(this);
        this.stopTime = stopTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Busstop getBusStop() {
        return busStop;
    }

    public void setBusStop(Busstop busStop) {
        this.busStop = busStop;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }
}
