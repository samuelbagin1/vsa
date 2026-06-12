/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Gmod4
 */

@Singleton
@Path("skuska")
public class SkuskaResource {

    @Context
    private UriInfo context;
    
    ArrayList<Skuska> skusky = new ArrayList<>();

    /**
     * Creates a new instance of SkuskaResource
     */
    public SkuskaResource() {
        Skuska s = new Skuska();
        s.setPredmet("VSA");
//        s.getStudenti().add("Marek Pecar");
        s.setTermin("12-02-2021");
        
        skusky.add(s);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void post1(Skuska xml) {
        boolean obsahuje = false;
        for (Skuska s: skusky) {
            if (s.getPredmet().equals(xml.getPredmet())) {
                obsahuje = true;
                break;
            }
        }
        
        if (!obsahuje) {
            skusky.add(xml);
        }
    }
    
    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getSkuskaPredmet(@PathParam("predmet") String predmet) {
        if (predmet == null) {
            return 0;
        }
        
        for (Skuska s: skusky) {
            if (s.getPredmet().equals(predmet)) {
                return s.getStudenti().size();
            }
        }
        
        return 0;
        
    }
    
    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getSkuskaPredmetXML(@PathParam("predmet") String predmet) {
        System.out.println("test");
        if (predmet == null) {
            return null;
        }
        
        for (Skuska s: skusky) {
            if (s.getPredmet().equals(predmet)) {
                return s;
            }
        }
        
        return null;
        
    }
    
    @POST
    @Path("{predmet}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String prihlasStudenta(@PathParam("predmet") String predmet, String student) {
        if (predmet == null) {
            return null;
        }
        
        boolean obsahuje = false;
        Skuska curS = null;
        for (Skuska s: skusky) {
            if (s.getPredmet().equals(predmet)) {
                obsahuje = true;
                curS = s;
                break;
            }
        }
        
        if (!obsahuje) {
            return "zly predmet";
        }
        
        if (curS.getStudenti().contains(student)) {
            return curS.getTermin();
        }
        
        curS.getStudenti().add(student);

        return curS.getTermin();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Retrieves representation of an instance of rest.SkuskaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getXml() {
        //TODO return proper representation object
//        throw new UnsupportedOperationException();

        return skusky.get(0);
    }

    /**
     * PUT method for updating or creating an instance of SkuskaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
