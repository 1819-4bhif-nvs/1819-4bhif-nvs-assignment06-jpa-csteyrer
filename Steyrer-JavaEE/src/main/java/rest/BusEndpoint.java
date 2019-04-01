package rest;

import model.Bus;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


    @Path("bus")
    @Stateless
    public class BusEndpoint {

        @PersistenceContext
        EntityManager em;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAll() {
            List<Bus> results = em.createNamedQuery("Bus.findAll", Bus.class).getResultList();
            JsonObjectBuilder builder = Json.createObjectBuilder();
            for(Bus bus : results)
            {
                builder.add("name", bus.getDriverName());
            }
            return Response.ok().entity(builder.build()).build();
        }

        @Path("{id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getCrewMembers(@PathParam("id") long id) {
            Bus cm = em.find(Bus.class, id);
            if(cm != null){
                return Response.ok().entity(cm).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        @DELETE
        @Path("{id}")
        public Response delete(@PathParam("id") long id){
            Bus cm = em.find(Bus.class, id);
            if(cm != null){
                em.remove(cm);
            }
            return Response.noContent().build();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response post(Bus cm){
            try {
                em.persist(cm);
                em.flush();
                em.refresh(cm);
            }catch(PersistenceException e){
                return Response.status(400).build();
            }
            return Response.ok().entity(cm).build();
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response put(Bus cm){
            cm = em.merge(cm);
            em.flush();
            em.refresh(cm);
            return Response.ok().entity(cm).build();
        }
    }
