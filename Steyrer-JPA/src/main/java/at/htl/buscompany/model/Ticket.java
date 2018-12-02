package at.htl.buscompany.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    @ManyToOne
    private Bus bus;

    @Column(name = "DTYPE", insertable = false, updatable = false)
    private String dType;

    //region Constructor
    public Ticket() {
    }

    public Ticket(double price) {
        this.price = price;
    }

    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion
}
