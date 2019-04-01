//package st;
//
//import model.Bus;
//import org.junit.Before;
//import org.junit.Test;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonObjectBuilder;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class busCompanyTests {
//
//    private Client client;
//    private WebTarget tut;
//
//    @Before
//    public void initClient(){
//        this.client = ClientBuilder.newClient();
//        this.tut = client.target("http://localhost:8084/probe/api/");
//    }
//
//    @Test
//    public void t01_setuptestdata(){
//        JsonObjectBuilder t = Json.createObjectBuilder();
//        t.add("driverName", "Herbert");
//        t.add("busType", "typ2");
//        JsonObject obj = t.build();
//
//        putGetDeleteGet(obj, "bus", new String[]{"/driverName", "/busType"});
//    }
//
//    @Test
//    public void t01_addAndRemoveTeacher(){
//        JsonObjectBuilder t = Json.createObjectBuilder();
//        t.add("firstName", "test");
//        t.add("lastName", "test");
//        t.add("teacherNumber", "TEST321");
//        JsonObject obj = t.build();
//
//        putGetDeleteGet(obj, "teachers", new String[]{"/firstName", "/lastName", "/teacherNumber"});
//    }
//
//    @Test
//    public void t02_addAndRemoveStudent(){
//        JsonObjectBuilder t = Json.createObjectBuilder();
//        t.add("firstName", "test");
//        t.add("lastName", "test");
//        t.add("matNumber", "TEST321");
//        JsonObject obj = t.build();
//
//        putGetDeleteGet(obj, "students", new String[]{"/firstName", "/lastName", "/matNumber"});
//    }
//
//    @Test
//    public void t03_addAndRemoveCourse(){
//        JsonObjectBuilder t = Json.createObjectBuilder();
//        t.add("name", "test123");
//        t.add("subject", Json.createObjectBuilder().add("id", 1));
//        t.add("teacher", Json.createObjectBuilder().add("id", 4));
//        t.add("date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
//        JsonObject obj = t.build();
//
//        putGetDeleteGet(obj, "courses", new String[]{"/name", "/subject/id", "/teacher/id"});
//    }
//
//    @Test
//    public void t04_addAndRemoveEnrolment(){
//        JsonObjectBuilder t = Json.createObjectBuilder();
//        t.add("date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
//        t.add("course", Json.createObjectBuilder().add("id", 11));
//        t.add("student", Json.createObjectBuilder().add("id", 16));
//        JsonObject obj = t.build();
//
//        putGetDeleteGet(obj, "enrolments", new String[]{"/date", "/student/id", "/course/id"});
//    }
//
//    private void putGetDeleteGet(JsonObject toPost, String path, String[] compareVals){
//
//        Response resp = this.tut.path(path).request(MediaType.APPLICATION_JSON).put(Entity.json(toPost.toString()));
//        assertThat(resp.getStatus(), is(200));
//        //Test Idempodence
//        int idempodencePutId = resp.readEntity(JsonObject.class).getInt("id");
//        JsonObject idempodencePut = Json.createObjectBuilder(toPost).add("id",idempodencePutId).build();
//        resp = this.tut.path(path).request(MediaType.APPLICATION_JSON).put(Entity.json(idempodencePut.toString()));
//        assertThat(resp.getStatus(), is(200));
//        JsonObject inserted = resp.readEntity(JsonObject.class);
//        assertThat(inserted, is(notNullValue()));
//        int id = inserted.getInt("id");
//
//        assertThat(idempodencePutId, equalTo(id));
//
//        JsonObjectBuilder exBuilder = Json.createObjectBuilder(toPost);
//        exBuilder.add("id",id);
//
//        Response get = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();
//
//        JsonObject expected = exBuilder.build();
//        JsonObject actual = get.readEntity(JsonObject.class);
//
//        for(String s:compareVals){
//            assertThat(actual.getValue(s), equalTo(expected.getValue(s)));
//        }
//
//        Response del = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
//        assertThat(del.getStatus(), is(204));
//        //Test Idempodence
//        del = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
//        assertThat(del.getStatus(), is(204));
//
//        Response getDel = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();
//        assertThat(getDel.getStatus(), is(404));
//    }
//
//}
