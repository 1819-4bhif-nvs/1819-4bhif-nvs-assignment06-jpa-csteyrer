package at.htl.buscompany.rest;

import at.htl.buscompany.business.InitBean;
import at.htl.buscompany.model.Bus;

import javax.decorator.Delegate;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

@Path("bus")
@Stateful
public class BusEndpoint {

    @PersistenceContext
    EntityManager em;
    @GET
    public List<Bus> getBuses()
    {
        return em.createNamedQuery("Bus.findAll", Bus.class).getResultList();
    }

    @GET
    @Path("{name}")
    public Bus getBusByDriverName(@PathParam("name") String driverName) {
        return em.createNamedQuery("Bus.findByDriverName", Bus.class)
                .setParameter("DRIVERNAME", driverName).getSingleResult();
    }

    @GET
    @Path("{id}")
    public Bus getBus(@PathParam("id") long id) {
        return em.find(Bus.class, id);
    }

    @POST
    public void postBus(Bus bus) {
        for (int i = 0; i < 15; i++)
            System.out.println();
        System.out.println(bus);
        em.persist(bus);
    }

    @PUT
    @Path("{id}")
    public void putBus(@PathParam("id") long id, Bus newBus)
    {
        System.out.println(newBus.getId());
        Bus bus = em.find(Bus.class, id);
        if(bus != null) {
            bus.setDriverName(newBus.getDriverName());
            bus.setBusType(newBus.getBusType());
        }
        else
            bus = new Bus(newBus.getDriverName(), newBus.getBusType());
        em.merge(bus);
    }

    @DELETE
    @Path("{id}")
    public void deleteBus(@PathParam("id") long id)
    {
        Bus bus = em.find(Bus.class, id);
        if(bus != null)
        {
            em.remove(bus);
        }
    }


}
