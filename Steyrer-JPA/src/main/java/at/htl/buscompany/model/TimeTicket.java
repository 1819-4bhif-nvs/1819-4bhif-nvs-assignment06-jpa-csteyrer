package at.htl.buscompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TimeTicket extends Ticket {

    private int hours;

    //region Constructor
    public TimeTicket() {
    }

    public TimeTicket(double price, LocalDateTime buyingTime,BusStop busStop, int hours) {
        super(price, buyingTime, busStop);
        this.hours = hours;
    }

    //endregion

    //region Getter and Setter

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    //endregion
}
