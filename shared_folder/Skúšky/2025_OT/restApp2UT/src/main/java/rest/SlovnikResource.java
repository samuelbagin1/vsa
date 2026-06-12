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
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author edu
 */
@Singleton
@Path("slovnik")
public class SlovnikResource {
    private Map<String, Map<String, String>> slovnik; // word - lang - preklad
    
    public SlovnikResource() {
        slovnik = new HashMap<>();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int getPocetSlov() {
        if (slovnik == null || slovnik.isEmpty()) {
            return 0;
        }
        
        return slovnik.size();
    }
    
    @GET
    @Path("{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZoznamJazykov(@PathParam("word") String word) {
        if (word == null || word.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        Map<String, String> lang = slovnik.get(word);
        if (lang == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(lang.keySet()).build();
    }
    
    @DELETE
    @Path("{word}")
    public void deleteWord(@PathParam("word") String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        Map<String, String> lang = slovnik.get(word);
        if (lang == null) {
            return;
        }
        
        slovnik.remove(word);
    }
    
    
    @GET
    @Path("{word}/{lang}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPreklad(@PathParam("word") String word, @PathParam("lang") String lang) {
        if (word == null || slovnik.get(word) == null || word.isEmpty()) {
            return "Nezname slovo";
        }
        
        if (lang == null || slovnik.get(word).get(lang) == null || word.isEmpty()) {
            return "Preklad neexistuje";
        }
        
        return slovnik.get(word).get(lang);
    }
    
    @PUT
    @Path("{word}/{lang}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putPreklad(@PathParam("word") String word, @PathParam("lang") String lang, String preklad) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        if (slovnik.get(word) == null) {
            slovnik.put(word, new HashMap<>());
        }
        
        if (lang == null || word.isEmpty()) {
            return;
        }
        
        slovnik.get(word).put(lang, preklad);
    }
    
    @DELETE
    @Path("{word}/{lang}")
    public void deletePreklad(@PathParam("word") String word, @PathParam("lang") String lang) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        if (lang == null || word.isEmpty()) {
            return;
        }
        
        slovnik.get(word).remove(lang);
    }
    
}
