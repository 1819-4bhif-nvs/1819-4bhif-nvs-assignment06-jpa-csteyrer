package business;

import model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init)
    {
        Bus bus = new Bus("typ1", "Franz");
        Busstop busStop = new Busstop("Busstop1");
        Schedule s1 = new Schedule(bus, busStop, LocalDateTime.of(2018, 12, 1, 10, 5));
        Ticket t1 = new TimeTicket(busStop, LocalDateTime.of(2018, 12, 1, 10, 5), 5, 3);

        t1.setBus(bus);

        em.persist(bus);
        em.persist(busStop);
        em.persist(s1);
        em.persist(t1);

    }
}
