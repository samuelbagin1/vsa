/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GenericResource
 * [poistenie]<br>
 * USAGE:
 * <pre>
        RestClient client = new RestClient();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author edu
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
//    private static final String BASE_URI = "http://localhost:9999/RestClientTest/resources";
    private static final String BASE_URI = "http://localhost:8080/restapp/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("poistenie");
    }

    public <T> T getOsoby(Class<T> responseType, String zmluva) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/poistenci", new Object[]{zmluva}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getZmluva(Class<T> responseType, String zmluva) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{zmluva}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void postZmluva(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public String postOsoba(Object requestEntity, String zmluva) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{zmluva})).request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public <T> T getOsoba(Class<T> responseType, String zmluva, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{zmluva, pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void deleteOsoba(String zmluva, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{zmluva, pid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
