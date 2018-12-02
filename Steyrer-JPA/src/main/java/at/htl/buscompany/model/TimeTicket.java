package at.htl.buscompany.model;

import javax.persistence.Entity;

@Entity
public class TimeTicket extends Ticket {

    private int hours;

    //region Constructor
    public TimeTicket() {
    }

    public TimeTicket(double price, int hours) {
        super(price);
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
