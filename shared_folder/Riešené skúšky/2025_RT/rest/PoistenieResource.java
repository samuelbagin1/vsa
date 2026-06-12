/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author edu
 */

@Singleton
@Path("poistenie")
public class PoistenieResource {
    List<Poistenie> poistenia;

    public PoistenieResource() {
        poistenia = new ArrayList<>();
        Osoba majitel = new Osoba();
        majitel.setMeno("Ferko");
        majitel.setRc("2001");
        majitel.setAdresa("Hlavna 22");
        
        Poistenie poistenie = new Poistenie();
        poistenie.setIdZmluvy("Z123");
        poistenie.setPoistnaSuma(299.0);
        poistenie.setVlastnik(majitel);
        poistenie.setPocetPoistencov(0);
        poistenie.setZoznamOsob(new ArrayList<>());
        
        poistenia.add(poistenie);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void vytvorZmluvu(Poistenie poistenie) {
        if (poistenie != null) {
            poistenie.setPocetPoistencov(0);
            poistenie.setZoznamOsob(new ArrayList<>());
            poistenia.add(poistenie);
        }
    }
    
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Poistenie getZmluvu(@PathParam("id") String id) {
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
    public String pridajOsobuDoZmluvy(@PathParam("id") String id, Osoba osoba) {
        if (id == null || osoba == null) {
            return null;
        }
        
        int poradoveCislo = 0;
        
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                p.getZoznamOsob().add(osoba);
                p.setPocetPoistencov(p.getZoznamOsob().size());
                poradoveCislo = p.getZoznamOsob().size();
                return String.valueOf(poradoveCislo);
            }
        }
        
        return null;
    }
    
    @GET
    @Path("{id}/{no}")
    @Produces(MediaType.APPLICATION_XML)
    public Osoba getPoistenaOsoba(@PathParam("id") String id, @PathParam("no") int no) {
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                if (no >= 1 && no <= p.getZoznamOsob().size()) {
                    return p.getZoznamOsob().get(no - 1);
                }
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}/{no}")
    public void deleteOsoba(@PathParam("id") String id, @PathParam("no") int no) {
        for (Poistenie p : poistenia) {
            if (p.getIdZmluvy().equals(id)) {
                if (no >= 1 && no <= p.getZoznamOsob().size()) {
                    p.getZoznamOsob().remove(no - 1);
                    p.setPocetPoistencov(p.getZoznamOsob().size());
                    break;
                }
            }
        }
        return;
    }
    
    
    
}
