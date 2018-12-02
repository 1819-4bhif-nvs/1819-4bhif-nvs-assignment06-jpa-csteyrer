package at.htl.buscompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RouteTicket extends Ticket {

    private int stops;

    //region Constructor
    public RouteTicket() {
    }

    public RouteTicket(double price, int stops)
    {
        super(price);
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
