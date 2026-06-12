/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author edu
 */
@Path("/poistenie")
@Singleton
public class Controller {
    
    @Context
    private UriInfo context;
    
    private ArrayList<Zmluva> zmluvy;

    public Controller() {
        zmluvy = new ArrayList<>();
        Zmluva z = new Zmluva();
        
        z.setIdZmluvy("Z123");
        
        Osoba v = new Osoba();
        v.setMeno("Mrkva");
        v.setRocnik("2001");
        v.setAdresa("Hlavna 22");
        
        z.setVlastnik(v);
        z.setPocet(0);
        zmluvy.add(z);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void createZmluva(Zmluva zmluva){
        zmluvy.add(zmluva);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Zmluva getZmluva(@PathParam("id") String id){
        for (Zmluva z : zmluvy){
            if (z.getIdZmluvy().equals(id)){
                return z;
            }
        }
        return null;
    }
    
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addOwner(@PathParam("id") String id, Osoba vlastnik){
        for (Zmluva z : zmluvy){
            if(z.getIdZmluvy().equals(id)){
                z.setPocet(z.getPocet()+1);
                z.getPoistenci().add(vlastnik);
                return z.getPoistenci().size() + "";
            }
        }
        return null;
    }
    

    @GET
    @Path("/{id}/{no}")
    @Produces(MediaType.APPLICATION_XML)
    public Osoba getVlastnik(@PathParam("id") String zmluvaId, @PathParam("no") int vlastnikPoradie){
        for (Zmluva z : zmluvy){
            if (z.getIdZmluvy().equals(zmluvaId)){
                if (z.getPoistenci().size() >= vlastnikPoradie){
                    return z.getPoistenci().get(vlastnikPoradie-1);
                }
            }
        }
        return null;
    }
    
    @DELETE
    @Path("/{id}/{no}")
    public void deleteOsoba(@PathParam("id")String zmluvaId,@PathParam("no") int vlastnikPoradie){
        for (Zmluva z : zmluvy){
            if (z.getIdZmluvy().equals(zmluvaId)){
                if (z.getPoistenci().size() >= vlastnikPoradie){
                    z.getPoistenci().remove(z.getPoistenci().get(vlastnikPoradie-1));
                    z.setPocet(z.getPocet()-1);
                }
            }
        }
    }
}
