package model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Bus.findAllSchedules", query = "select b from Bus b where b.busType = 'typ1'"),
        @NamedQuery(name = "Bus.findAllTickets", query = "select b from Bus b where b.driverName = 'Franz'"),
        @NamedQuery(name = "Bus.findAll", query = "select b from Bus b")
})
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "guest")
    @JsonbTransient
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.REMOVE)
    @JsonbTransient
    private List<Schedule> scheduleList;

    private String busType;

    private String driverName;

    public Bus() {
    }

    public Bus(String busType, String driverName) {
        this.scheduleList = new LinkedList<>();
        this.tickets = new LinkedList<>();
        this.busType = busType;
        this.driverName = driverName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setSchedule(Schedule schedule) {
        scheduleList.add(schedule);
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
