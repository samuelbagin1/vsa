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

/**
 *
 * @author edu
 */

@Singleton
@Path("poistenie")
public class PoistenieResource {
    private List<Poistenie> poistenci;
    
    public PoistenieResource() {
        this.poistenci = new ArrayList<>();
        Osoba o = new Osoba();
        o.setAdresa("Hlavna 22");
        o.setMeno("Ferko");
        o.setRc(2001);
        Poistenie p = new Poistenie();
        p.setIdZmluvy("Z123");
        p.setPoistnaSuma(299.00);
        p.setOsoby(new ArrayList<>());
        p.setPocetPoistencov(p.getOsoby().size());
        p.setVlastnik(o);
        poistenci.add(p);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void createPoistenie(Poistenie p) {
        if (p == null) return;

        // Vždy začni s nulovým počtom poistencov
        p.setOsoby(new ArrayList<>());
        p.setPocetPoistencov(0);

        // Pridaj iba ak neexistuje zmluva s rovnakým ID
        for (Poistenie po : poistenci) {
            if (po.getIdZmluvy().equals(p.getIdZmluvy())) return;
        }

        poistenci.add(p);
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Poistenie getPoistenie(@PathParam("id") String id) {
        for(Poistenie p : poistenci) {
            if(p.getIdZmluvy().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    @Path("{id}")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addPoistenec (@PathParam("id") String id, Osoba o) {
        for(Poistenie p : poistenci) {
            if (p != null) {
                if(p.getIdZmluvy().equals(id)) {
                    if(p.getOsoby() != null) {
                        p.getOsoby().add(o);
                        p.setPocetPoistencov(p.getOsoby().size());
                        return String.valueOf(p.getOsoby().size());
                    }
                    else {
                        p.setOsoby(new ArrayList<>());
                        p.getOsoby().add(o);
                        p.setPocetPoistencov(p.getOsoby().size());
                        return String.valueOf(p.getOsoby().size());
                    }
                }
            }
        }
        return null;
    }
    
    @Path("{id}/{no}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Osoba getPoistenec(@PathParam("id") String id, @PathParam("no") int n) {
        for(Poistenie p : poistenci) {
            if (p != null) {
                if(p.getIdZmluvy().equals(id)) {
                    if(n >= 0 && n <= p.getOsoby().size()) {
                        return p.getOsoby().get(n - 1);
                    }
                }
            }
        }
        
        return null;
    }
    
    @Path("{id}/{no}")
    @DELETE
    public void deleteOsoba(@PathParam("id") String id, @PathParam("no") int n) {
        for(Poistenie p : poistenci)  {
            if(p != null) {
                if(p.getIdZmluvy().equals(id)) {
                    if(n >= 0 && n <= p.getOsoby().size()) {
                        p.getOsoby().remove(n - 1);
                        p.setPocetPoistencov(p.getOsoby().size());
                        return;
                    }
                }
            }
        }
        return;
    }
    
    
    
}
