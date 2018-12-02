package at.htl.buscompany.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class BusCompanyEndpointsIT {
    private Client client;
    private WebTarget target;

    @Before
    public void initClient()
    {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8084/steyrer-jpa");
    }

    @Test
    public void test_01() {
        WebTarget target = this.target.path("bus");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonArray.class).get(0);

        String driverName = jsonValue.asJsonObject().getString("driverName");
        System.out.println(driverName);

        assertThat(driverName, is("Franz"));
        //---------------------------------------------------------------------------------
        JsonObject busJson = Json.createObjectBuilder()
                .add("driverName", "Herbert")
                .add("busType", "Gelenkbus")
                .build();
        response = target.request().post(Entity.json(busJson));
        assertThat(response.getStatus(), is(204));
        response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        jsonValue = response.readEntity(JsonArray.class).get(1);

        driverName = jsonValue.asJsonObject().getString("driverName");
        System.out.println(driverName);

        assertThat(driverName, is("Herbert"));
        //---------------------------------------------------------------------------------
        busJson = Json.createObjectBuilder()
                .add("driverName", "Max")
                .add("busType", "Gelenkbus")
                .build();
        response = target.path("2").request().put(Entity.json(busJson));
        assertThat(response.getStatus(), is(204));
        response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        jsonValue = response.readEntity(JsonArray.class).get(1);

        driverName = jsonValue.asJsonObject().getString("driverName");
        System.out.println(driverName);

        assertThat(driverName, is("Max"));
        //---------------------------------------------------------------------------------
        //reset
        response = target.path("2").request().delete();
        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void test_02() {
        Response response = this.target.path("busStop/1").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        String busStopName = jsonValue.asJsonObject().getString("busStopName");
        System.out.printf(busStopName);

        assertThat(busStopName, is("Haltestelle-Leonding"));
    }

    @Test
    public void test_03() {
        Response response = this.target.path("shedule/1").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonValue jsonValue = response.readEntity(JsonValue.class);

        String stopTime = jsonValue.asJsonObject().getString("stopTime");
        System.out.printf(stopTime);

        assertThat(stopTime, is("2018-12-01T18:30:00"));
    }

}
