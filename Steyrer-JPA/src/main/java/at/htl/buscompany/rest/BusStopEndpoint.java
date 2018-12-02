package at.htl.buscompany.rest;

import at.htl.buscompany.model.Bus;
import at.htl.buscompany.model.BusStop;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

@Path("busStop")
@Stateful
public class BusStopEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    public BusStop getBusStop(@PathParam("id") long id) {
        return em.find(BusStop.class, id);
    }

    @POST
    public void postBusStop(BusStop busStop) {
        em.persist(busStop);
    }

    @PUT
    @Path("{id}/{busStopName}")
    public void putBusStop(@PathParam("id") long id, @PathParam("busStopName") String busStopName)
    {
        BusStop busStop = em.find(BusStop.class, id);
        if(busStop != null) {
            busStop.setBusStopName(busStopName);
        }
        else
            busStop = new BusStop(busStopName);
        em.merge(busStop);
    }
}
