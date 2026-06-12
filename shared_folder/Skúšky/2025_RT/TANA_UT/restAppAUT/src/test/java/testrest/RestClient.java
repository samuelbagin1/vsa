/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrest;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class RestClient {


    private WebTarget webTarget;
    private Client client;
//    private static final String BASE_URI = "http://localhost:9999/RestUnitTest/resources";
    private static final String BASE_URI = "http://localhost:8080";

    public RestClient() {
        client = ClientBuilder.newClient();
//        webTarget = client.target(BASE_URI).path("ponuka");
        webTarget = client.target(BASE_URI).path("poistenie");
     }

    public <T> T getOsoby(Class<T> responseType, String zmluva) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/poistenci", new Object[]{zmluva}));
        return resource.request(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getZmluva(Class<T> responseType, String zmluva) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{zmluva}));
        return resource.request(MediaType.APPLICATION_XML).get(responseType);
    }

    public void postZmluva(Object requestEntity) throws ClientErrorException {
        webTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(requestEntity, MediaType.APPLICATION_XML));
    }

    public String postOsoba(Object requestEntity, String zmluva) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{zmluva})).request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(requestEntity, MediaType.APPLICATION_XML), String.class);
    }

    public <T> T getOsoba(Class<T> responseType, String zmluva, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{zmluva, pid}));
        return resource.request(MediaType.APPLICATION_XML).get(responseType);
    }

    public void deleteOsoba(String zmluva, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{zmluva, pid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
