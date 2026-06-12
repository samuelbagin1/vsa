/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ubuntu
 */
@Path("ponuka")
@Singleton
public class PonukaResource {
    private Map<String, List<Jedlo>> ponuka;
    
    public PonukaResource() {
        ponuka = new HashMap<>();
        
        Jedlo jedlo = new Jedlo();
        jedlo.setCena(3.5);
        jedlo.setNazov("gulas");
        
        List<Jedlo> jedla = new ArrayList<>();
        jedla.add(jedlo);
        
        ponuka.put("20-5-2024", jedla);
    }
    
    
    @GET
    @Path("{datum}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount(@PathParam("datum") String datum) {
        List<Jedlo> ponukaDna = ponuka.get(datum);
        return ponukaDna!=null ? String.valueOf(ponukaDna.size()) : "0";
    }
    
    @POST
    @Path("{datum}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String createJedlo(@PathParam("datum") String datum, Jedlo jedlo) {
        List<Jedlo> ponukaDna = ponuka.get(datum);
        
        if (jedlo == null) {
            return "0";
        }
        
        if (ponukaDna == null) {
            ponukaDna = new ArrayList<>();
            ponukaDna.add(jedlo);
            ponuka.put(datum, ponukaDna);
            
            return String.valueOf(ponukaDna.size());
        }
        
        for (Jedlo j : ponukaDna) {
            if (j.getNazov().equals(jedlo.getNazov())) {
                return String.valueOf(ponukaDna.indexOf(j) + 1);
            }
        }
        
        ponuka.get(datum).add(jedlo);
        return String.valueOf(ponuka.get(datum).size());
        
    }
    
    
    
    @GET
    @Path("{datum}/{n}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("datum") String datum, @PathParam("n") int n) {
        if (datum!=null && !datum.isEmpty()) {
            List<Jedlo> ponukaDna = ponuka.get(datum);
            
            if (ponukaDna != null && n>=1 && n<=ponukaDna.size()) {
                return ponukaDna.get(n - 1);
            }
            
            return null;
        }
        
        return null;
    }
    
    @DELETE
    @Path("{datum}/{n}")
    public void deleteJedlo(@PathParam("datum") String datum, @PathParam("n") int n) {
        if (datum!=null && !datum.isEmpty()) {
            List<Jedlo> ponukaDna = ponuka.get(datum);
            if (ponukaDna != null && n>=1 && n<=ponukaDna.size()) {
                ponuka.get(datum).remove(n - 1);
            }
        }
    }
}
