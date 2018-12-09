package at.htl.buscompany.rest;

import at.htl.buscompany.model.Bus;
import at.htl.buscompany.model.BusStop;
import at.htl.buscompany.model.TimeTicket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("timeTicket")
@Stateless
public class TimeTicketEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TimeTicket getTimeTicket(@PathParam("id") long id) {
        return em.find(TimeTicket.class, id);
    }

    @POST
    @Path("{busId}")
    public Response postTimeTicket(@PathParam("busId") long busId,TimeTicket timeTicket) {
        Bus bus = em.find(Bus.class, busId);
        if(bus == null) return Response.status(404).build();

        timeTicket.setBus(bus);
        bus.addTicket(timeTicket);
        em.persist(timeTicket);

        return Response.noContent().build();
    }

    @PUT
    @Path("{busId}/{id}")
    public Response putTimeTicket(@PathParam("busId") long busId, @PathParam("id") long id, TimeTicket newTimeTicket)
    {
        Bus bus = em.find(Bus.class, busId);
        if(bus == null) return Response.status(404).build();

        TimeTicket timeTicket = em.find(TimeTicket.class, id);
        if(timeTicket != null) {
            timeTicket.getBus().removeTicket(timeTicket);
            bus.addTicket(timeTicket);
            timeTicket.setPrice(newTimeTicket.getPrice());
            timeTicket.setHours(newTimeTicket.getHours());
        }
        else {
            newTimeTicket.setBus(bus);
            bus.addTicket(newTimeTicket);
            em.persist(newTimeTicket);
        }

        return Response.noContent().build();
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
