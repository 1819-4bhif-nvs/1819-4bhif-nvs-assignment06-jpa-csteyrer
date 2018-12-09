package at.htl.buscompany.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TicketEndpointsIT {
    Client client;
    WebTarget target;

    @Before
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8084/steyrer-jpa");
    }


    @Test
    public void test_01() {
        Response response = this.target.path("timeTicket/1").request().delete();
        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void test_02() {
        WebTarget target = this.target.path("timeTicket");

        //---------------------------------------------------------------------------------
        JsonObject timeTicketJson = Json.createObjectBuilder()
                .add("price", 2)
                .add("hours", 3)
                .build();
        Response response = target.path("1/2").request().put(Entity.json(timeTicketJson)); //( post )
        assertThat(response.getStatus(), is(204));
        response = target.path("2").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        int hours = jsonValue.asJsonObject().getInt("hours");
        System.out.println(hours);

        assertThat(hours, is(3));
        //---------------------------------------------------------------------------------
        timeTicketJson = Json.createObjectBuilder()
                .add("price", 5)
                .add("hours", 10)
                .build();
        response = target.path("1/2").request().put(Entity.json(timeTicketJson));
        assertThat(response.getStatus(), is(204));
        response = target.path("2").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        jsonValue = response.readEntity(JsonValue.class);

        hours = jsonValue.asJsonObject().getInt("hours");
        System.out.println(hours);

        assertThat(hours, is(10));
        //---------------------------------------------------------------------------------
    }

    @Test
    public void test_03() {
        WebTarget target = this.target.path("routeTicket");

        JsonObject timeTicketJson = Json.createObjectBuilder()
                .add("price", 1)
                .add("hours", 1)
                .build();
        Response response = this.target.path("timeTicket/1/2").request().put(Entity.json(timeTicketJson)); //( post )
        assertThat(response.getStatus(), is(204));

        JsonObject routeTicketJson = Json.createObjectBuilder()
                .add("price", 1)
                .add("stops", 1)
                .build();
        response = target.path("1/3").request().put(Entity.json(routeTicketJson)); //( post )
        assertThat(response.getStatus(), is(204));

        response = target.path("3").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        int stops = jsonValue.asJsonObject().getInt("stops");
        System.out.println(stops);

        assertThat(stops, is(1));

        //---------------------------------------------------------------------------------
        routeTicketJson = Json.createObjectBuilder()
                .add("price", 2)
                .add("stops", 3)
                .build();
        response = target.path("1/3").request().put(Entity.json(routeTicketJson)); //( post )
        assertThat(response.getStatus(), is(204));
        response = target.path("3").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        jsonValue = response.readEntity(JsonValue.class);

        stops = jsonValue.asJsonObject().getInt("stops");
        System.out.println(stops);

        assertThat(stops, is(3));
        //---------------------------------------------------------------------------------
        routeTicketJson = Json.createObjectBuilder()
                .add("price", 5)
                .add("stops", 10)
                .build();
        response = target.path("1/3").request().put(Entity.json(routeTicketJson));
        assertThat(response.getStatus(), is(204));
        response = target.path("3").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        jsonValue = response.readEntity(JsonValue.class);

        stops = jsonValue.asJsonObject().getInt("stops");
        System.out.println(stops);

        assertThat(stops, is(10));
        //---------------------------------------------------------------------------------
    }

}
