package at.htl.buscompany.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
        Response response = this.target.path("timeTicket/1").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        int hours = jsonValue.asJsonObject().getInt("hours");
        System.out.print(hours);

        assertThat(hours, is(1));
    }

    @Test
    public void test_02() {
        Response response = this.target.path("routeTicket/1").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        int stops = jsonValue.asJsonObject().getInt("stops");
        System.out.print(stops);

        assertThat(stops, is(1));
    }

}
