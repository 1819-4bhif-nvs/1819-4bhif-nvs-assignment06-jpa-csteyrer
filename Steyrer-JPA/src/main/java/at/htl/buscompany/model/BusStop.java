package at.htl.buscompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class BusStop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busStopName;

    //region Constructor
    public BusStop() {
    }

    public BusStop(String busStopName) {
        this.busStopName = busStopName;
    }
    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }
//endregion
}
