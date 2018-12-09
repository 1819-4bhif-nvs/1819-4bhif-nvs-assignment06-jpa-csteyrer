package at.htl.buscompany.rest;

import at.htl.buscompany.model.Bus;
import at.htl.buscompany.model.RouteTicket;
import at.htl.buscompany.model.TimeTicket;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("routeTicket")
@Stateless
public class RouteTicketEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public RouteTicket getRouteTicket(@PathParam("id") long id) {
        return em.find(RouteTicket.class, id);
    }

    @POST
    @Path("{busId}")
    public Response postRouteTicket(@PathParam("busId") long busId, RouteTicket routeTicket) {
        Bus bus = em.find(Bus.class, busId);
        if(bus == null) return Response.status(404).build();

        routeTicket.setBus(bus);
        bus.addTicket(routeTicket);
        em.persist(routeTicket);

        return Response.noContent().build();
    }

    @PUT
    @Path("{busId}/{id}")
    public Response putRouteTicket(@PathParam("busId") long busId, @PathParam("id") long id, RouteTicket newRouteTicket)
    {
        Bus bus = em.find(Bus.class, busId);
        if(bus == null) return Response.status(404).build();

        RouteTicket routeTicket = em.find(RouteTicket.class, id);
        if(routeTicket != null) {
            routeTicket.getBus().removeTicket(routeTicket);
            bus.addTicket(routeTicket);
            routeTicket.setPrice(newRouteTicket.getPrice());
            routeTicket.setStops(newRouteTicket.getStops());
        }
        else {
            newRouteTicket.setBus(bus);
            bus.addTicket(newRouteTicket);
            em.persist(newRouteTicket);
        }

        return Response.noContent().build();
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
