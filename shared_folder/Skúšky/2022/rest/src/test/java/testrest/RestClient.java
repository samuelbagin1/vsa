package testrest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080";

    public RestClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("skuska");
    }

    public String getPredmety() throws ClientErrorException {
        return webTarget.request(MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getPredmetyStudenta(String student) throws ClientErrorException {
        return webTarget.queryParam("student", student)
                .request(MediaType.TEXT_PLAIN).get(String.class);
    }

    public String postSkuska(Object requestEntity) throws ClientErrorException {
        return webTarget.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(requestEntity, MediaType.APPLICATION_XML), String.class);
    }

    public String getPocetStudentov(String predmet) throws ClientErrorException {
        return webTarget.path(predmet).request(MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T getSkuska(Class<T> responseType, String predmet) throws ClientErrorException {
        WebTarget resource = webTarget.path(predmet);
        return resource.request(MediaType.APPLICATION_XML).get(responseType);
    }

    public String postStudent(String predmet, String student) throws ClientErrorException {
        return webTarget.path(predmet).request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(student, MediaType.TEXT_PLAIN), String.class);
    }

    public void close() {
        client.close();
    }
}
