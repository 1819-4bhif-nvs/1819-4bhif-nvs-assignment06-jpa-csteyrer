package model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Busstop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "busStop")
    private List<Schedule> scheduleList;

    private String busStopName;

    public Busstop() {
    }

    public Busstop(String busStopName) {
        this.scheduleList = new LinkedList<>();
        this.busStopName = busStopName;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setSchedule(Schedule schedule) {
        scheduleList.add(schedule);
    }

    public String getBusStopName() {
        return busStopName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }
}
