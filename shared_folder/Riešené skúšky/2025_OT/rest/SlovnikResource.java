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

@Singleton
@Path("slovnik")
public class SlovnikResource {
    private Map<String, Map<String, String>> slovnik;

    public SlovnikResource() {
        slovnik = new HashMap<>();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPocetSlov() {
        int pocet = slovnik.size();
        return Response.ok(pocet).build();
    }
    
    @GET
    @Path("{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZoznamJazykov(@PathParam("word") String word) {
        
        Map<String, String> preklady = slovnik.get(word);
        
        if (preklady == null || preklady.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(preklady.keySet()).build();        
    }
    
    @DELETE
    @Path("{word}")
    public Response vymazSlovo(@PathParam("word") String word) {
        slovnik.remove(word);
        return Response.ok().build();
    }
    
    @GET
    @Path("{word}/{lang}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response prelozSlovo(@PathParam("word") String word, @PathParam("lang") String lang) {
        Map<String, String> preklady = slovnik.get(word);
        
        if (preklady == null || preklady.isEmpty()) {
            return Response.ok("Nezname slovo").build();
        }
        
        String preklad = preklady.get(lang);
        
        if (preklad == null) {
            return Response.ok("Preklad neexistuje").build();
        }
        
        return Response.ok(preklad).build();
    }
    
    @PUT
    @Path("{word}/{lang}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response nahradSlovo(@PathParam("word") String word, @PathParam("lang") String lang, String preklad) {
        Map<String, String> preklady = slovnik.get(word);
        
        if (preklady == null) {
            preklady = new HashMap<>();
            slovnik.put(word, preklady);
        }
        
        preklady.put(lang, preklad);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{word}/{lang}")
    public Response vymazPreklad(@PathParam("word") String word, @PathParam("lang") String lang) {
        Map<String, String> preklady = slovnik.get(word);
        
        if (preklady != null) {
            preklady.remove(lang);
            
            if (preklady.isEmpty()) {
                slovnik.remove(word);
            }
        }
        return Response.ok().build();
    }
    
    
    
    
    
}
