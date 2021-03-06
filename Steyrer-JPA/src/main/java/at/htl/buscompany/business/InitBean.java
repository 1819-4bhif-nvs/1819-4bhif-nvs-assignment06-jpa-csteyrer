package at.htl.buscompany.business;

import at.htl.buscompany.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Startup
@Singleton
public class InitBean {

    @PersistenceContext
    EntityManager em;

    public  InitBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("******************** hello ********************");

        Bus bus = new Bus("Franz", "Gelenkbus");
        em.persist(bus);

        BusStop busStop = new BusStop("Haltestelle-Leonding");
        em.persist(busStop);

        Schedule schedule = new Schedule(bus, busStop, LocalDateTime.of(2018, 12, 1, 18, 30));
        em.persist(schedule);

        TimeTicket ticket = new TimeTicket(2.5,LocalDateTime.of(2018, 12, 1, 18, 5),busStop, 1);
        ticket.setBus(bus);
        bus.addTicket(ticket);
        em.persist(ticket);
        em.merge(bus);

    }

}
