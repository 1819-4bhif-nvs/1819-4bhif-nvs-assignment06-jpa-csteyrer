package at.htl.buscompany.rest;

import at.htl.buscompany.model.BusStop;
import at.htl.buscompany.model.Shedule;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.time.LocalDateTime;

@Path("shedule")
@Stateful
public class SheduleEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public Shedule getShedule(@PathParam("id") long id) {
        return em.find(Shedule.class, id);
    }

    @POST
    public void postShedule(Shedule shedule) {
        em.persist(shedule);
    }

    @PUT
    @Path("{id}/{stopTime}")
    public void putShedule(@PathParam("id") long id, @PathParam("stopTime") String stopTime)
    {
        Shedule shedule = em.find(Shedule.class, id);
        if(shedule != null) {
            shedule.setStopTime(LocalDateTime.parse(stopTime));
            em.merge(shedule);
        }
    }
}
