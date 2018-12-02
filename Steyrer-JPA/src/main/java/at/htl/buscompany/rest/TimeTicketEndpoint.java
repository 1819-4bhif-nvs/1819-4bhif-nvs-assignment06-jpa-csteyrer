package at.htl.buscompany.rest;

import at.htl.buscompany.model.BusStop;
import at.htl.buscompany.model.Shedule;
import at.htl.buscompany.model.TimeTicket;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Path("timeTicket")
@Stateful
public class TimeTicketEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public TimeTicket getTimeTicket(@PathParam("id") long id) {
        return em.find(TimeTicket.class, id);
    }

    @POST
    public void postTimeTicket(TimeTicket timeTicket) {
        em.persist(timeTicket);
    }

    @PUT
    @Path("{id}/{hours}")
    public void putTimeTicket(@PathParam("id") long id, @PathParam("hours") int hours)
    {
        TimeTicket timeTicket = em.find(TimeTicket.class, id);
        if(timeTicket != null) {
            timeTicket.setHours(hours);
            em.merge(timeTicket);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteTimeTicket(@PathParam("id") long id) {
        TimeTicket timeTicket = em.find(TimeTicket.class, id);
        if(timeTicket != null) {
            timeTicket.getBus().removeTicket(timeTicket);
            em.remove(timeTicket);
        }
    }
}
