package model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class TimeTicket extends Ticket{

    private int hours;

    public TimeTicket()
    {

    }

    public TimeTicket(Busstop busStop, LocalDateTime buyingTime, double price, int hours) {
        super(busStop, buyingTime, price);
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
