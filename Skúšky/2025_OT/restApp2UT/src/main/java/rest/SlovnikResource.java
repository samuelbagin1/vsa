/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edu
 */
@Path("slovnik")
@Singleton
public class SlovnikResource {
    private Map<String, Map<String, String>> slovnik;
    
    public SlovnikResource() {
        slovnik = new HashMap<>();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  String getPocetSlov() {
        return String.valueOf(slovnik.size());
    }

    @Path("{word}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJazyky(@PathParam("word") String word) {
        Map<String, String> preklady = slovnik.get(word);
        if (preklady == null || preklady.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(preklady.keySet()).build();
    }
    
     @Path("{word}")
    @DELETE
    public void deleteSlovo(@PathParam("word") String word) {
        slovnik.remove(word);
    }
    
    @Path("{word}/{lang}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPreklad(@PathParam("word") String word, @PathParam("lang") String lang) {
        Map<String, String> preklady = slovnik.get(word);
        if (preklady == null) {
            return "Nezname slovo";
        }
        String preklad = preklady.get(lang);
        if (preklad == null) {
            return "Preklad neexistuje";
        }
        return preklad;
    }

    @Path("{word}/{lang}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putPreklad(@PathParam("word") String word, @PathParam("lang") String lang, String novyPreklad) {
        slovnik.computeIfAbsent(word, k -> new HashMap<>()).put(lang, novyPreklad);
    }

    @Path("{word}/{lang}")
    @DELETE
    public void deletePreklad(@PathParam("word") String word, @PathParam("lang") String lang) {
        Map<String, String> preklady = slovnik.get(word);
        if (preklady != null) {
            preklady.remove(lang);
            if (preklady.isEmpty()) {
                slovnik.remove(word); // Optional: odstráni slovo úplne, ak nemá žiadne preklady
            }
        }
    }
    
}
