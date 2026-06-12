/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ubuntu
 */
@Singleton
@Path("poistenie")
public class PoistenieResource {
    private List<Poistenie> poistenia;
    
    public PoistenieResource() {
        poistenia = new ArrayList<>();
        
        Osoba majitel = new Osoba();
        majitel.setMeno("Hrasko");
        majitel.setRc(2001);
        majitel.setBydlisko("Hlavna 22");
        
        Poistenie poistenie = new Poistenie();
        poistenie.setIdZmluvy("Z123");
        poistenie.setPoistnaSuma(199.0);
        poistenie.setMajitel(majitel);
        poistenie.setPoistenci(new ArrayList<>());
        poistenie.setPocetPoistencov(0);
        
        poistenia.add(poistenie);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void novyZdroj(Poistenie poistenie) {
        Poistenie p = new Poistenie();
        p.setIdZmluvy(poistenie.getIdZmluvy());
        p.setPoistnaSuma(poistenie.getPoistnaSuma());
        p.setMajitel(poistenie.getMajitel());
        p.setPoistenci(new ArrayList<>());
        p.setPocetPoistencov(0);
        
        poistenia.add(p);
    }
    
    
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Poistenie getById(@PathParam("id") String id) {
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                return p;
            }
        }
        
        return null;
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postById(@PathParam("id") String id, Osoba osoba) {
        if (osoba != null || id != null) {
            for (Poistenie p : poistenia) {
                if (p.getIdZmluvy().equals(id)) {
                    p.getPoistenci().add(osoba);
                    p.setPocetPoistencov(p.getPoistenci().size());
                    return String.valueOf(p.getPoistenci().size());
                }
            }
        }
        
        return null;
    }
    
    
    @GET
    @Path("{id}/{no}")
    @Produces(MediaType.APPLICATION_XML)
    public Osoba getByIdAndNumber(@PathParam("id") String id, @PathParam("no") int number) {
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                if (number >= 1 && number <= p.getPoistenci().size()) {
                    return p.getPoistenci().get(number - 1);
                }
            }
        }
        
        return null;
    }
    
    
    @DELETE
    @Path("{id}/{no}")
    public void deleteByIdAndNumber(@PathParam("id") String id, @PathParam("no") int number) {
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                if (number >= 1 && number <= p.getPoistenci().size()) {
                    p.getPoistenci().remove(number - 1);
                    p.setPocetPoistencov(p.getPoistenci().size());
                    break;
                }
            }
        }
    }
    
}
