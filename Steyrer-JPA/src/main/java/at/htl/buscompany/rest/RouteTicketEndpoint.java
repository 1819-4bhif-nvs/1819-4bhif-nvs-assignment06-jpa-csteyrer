package at.htl.buscompany.rest;

import at.htl.buscompany.model.RouteTicket;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Path("routeTicket")
@Stateful
public class RouteTicketEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public RouteTicket getRouteTicket(@PathParam("id") long id) {
        return em.find(RouteTicket.class, id);
    }

    @POST
    public void postRouteTicket(RouteTicket routeTicket) {
        em.persist(routeTicket);
    }

    @PUT
    @Path("{id}/{stops}")
    public void putRouteTicket(@PathParam("id") long id, @PathParam("stops") int stops)
    {
        RouteTicket routeTicket = em.find(RouteTicket.class, id);
        if(routeTicket != null) {
            routeTicket.setStops(stops);
            em.merge(routeTicket);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteRouteTicket(@PathParam("id") long id) {
        RouteTicket routeTicket = em.find(RouteTicket.class, id);
        if(routeTicket != null) {
            routeTicket.getBus().removeTicket(routeTicket);
            em.remove(routeTicket);
        }
    }
}
