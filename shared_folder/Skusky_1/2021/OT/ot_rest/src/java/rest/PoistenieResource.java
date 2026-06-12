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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Micha
 */
@Path("poistenie")
@Singleton
public class PoistenieResource {

    @Context
    private UriInfo context;
    
    ArrayList<Poistenie> poistenia = new ArrayList<>();

    /**
     * Creates a new instance of PoistenieResource
     */
    public PoistenieResource() {
        
        Poistenie p = new Poistenie();
        p.setIdZmluvy("Z123");
        p.setMajitel("Jozko Mrkvicka");
        
        poistenia.add(p);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void post1(Poistenie xml) {
        boolean ma = false;
        for (Poistenie p: poistenia) {
            if (p.getIdZmluvy().equals(xml.getIdZmluvy())) {
                ma = true;
                break;
            }
        }
        if (!ma) poistenia.add(xml);
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public int get1(@PathParam("id") String id) {
        if (id == null) return 0;
        
        for (Poistenie p: poistenia)
            if (p.getIdZmluvy().equals(id))
                return p.getPoistenci().size();
        
        return 0;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Poistenie get2(@PathParam("id") String id) {
        if (id == null) return null;
        
        for (Poistenie p: poistenia)
            if (p.getIdZmluvy().equals(id))
                return p;
        
        return null;
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String post2(@PathParam("id") String id, String meno) {
        if (id == null) return null;
        
        boolean ma = false;
        Poistenie p2 = null;
        for (Poistenie p: poistenia)
            if (id.equals(p.getIdZmluvy())) {
                ma = true;
                p2 = p;
                break;
            }
        
        if (!ma) return "neznama zmluva";
        if (!p2.getPoistenci().contains(meno)) p2.getPoistenci().add(meno);
        return "OK";
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String get3(@QueryParam("osoba") String osoba) {
        String zmluvy = "";
        
        if (osoba != null)
            for (Poistenie p: poistenia)
                if (p.getPoistenci().contains(osoba))
                    zmluvy += p.getIdZmluvy() + " ";
        return zmluvy;
    }

    /**
     * Retrieves representation of an instance of rest.PoistenieResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
//        TODO return proper representation object
        return null;
    }

    /**
     * PUT method for updating or creating an instance of PoistenieResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
