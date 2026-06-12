package clientTest;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestClient {


    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080";

    public RestClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("slovnik");
    }

    public String getPreklad(String slovo, String jazyk) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{slovo, jazyk}));
        return resource.request(MediaType.TEXT_PLAIN).get(String.class);
    }

//    public String getJazyky(String slovo) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{slovo}));
//        return resource.request(MediaType.TEXT_PLAIN).get(String.class);
//    }

    public <T> T getZoznam(String slovo, Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{slovo}));
        return resource.request(MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPocet(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.TEXT_PLAIN).get(responseType);
    }

    public void delSlovo(String slovo) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{slovo})).request().delete();
    }

    public void setPreklad(Object requestEntity, String slovo, String jazyk) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}"
                , new Object[]{slovo, jazyk})).request(MediaType.TEXT_PLAIN).put(Entity.entity(requestEntity
                        , MediaType.TEXT_PLAIN));
    }

    public void delPreklad(String slovo, String jazyk) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{slovo, jazyk})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
