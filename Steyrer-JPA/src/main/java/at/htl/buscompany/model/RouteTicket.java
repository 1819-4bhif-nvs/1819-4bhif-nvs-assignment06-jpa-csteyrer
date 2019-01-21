package at.htl.buscompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class RouteTicket extends Ticket implements Serializable {

    private int stops;

    //region Constructor
    public RouteTicket() {
    }

    public RouteTicket(double price,LocalDateTime buyingTime,BusStop busStop, int stops)
    {
        super(price, buyingTime,busStop);
        this.stops = stops;
    }

    //endregion

    //region Getter and Setter

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    //endregion
}
