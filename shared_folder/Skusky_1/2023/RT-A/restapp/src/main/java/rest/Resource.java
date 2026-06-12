/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;
import javax.inject.Singleton;
import java.util.ArrayList;
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
 * @author edu
 */
@Singleton
@Path("/poistenie")
public class Resource {
    private ArrayList<Poistenie> poistenia;

    public Resource() {
        poistenia = new ArrayList<>();
        
        Osoba m = new Osoba();
        m.setMeno("Hrasko");
        m.setRc("2001");
        m.setAdresa("Hlavna 22");
        
        Poistenie p = new Poistenie();
        p.setIdZmluvy("Z123");

        p.setMajitel(m);
        p.setPocetPoistencov(0);
        poistenia.add(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void createPoistenie(Poistenie poistenie) {
        poistenia.add(poistenie);
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Poistenie getZmluva(@PathParam("id") String id) {
        //return this.zmluvy.stream().filter(zmluva -> zmluva.getId().equals(id)).findFirst().orElse(null);
        for (Poistenie zmluva : poistenia) {
            if (zmluva.getIdZmluvy().equals(id))
                return zmluva;
        }
        return null;
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String addOsoba(@PathParam("id") String id, Osoba osoba) {
       for (Poistenie z : poistenia){
            if(z.getIdZmluvy().equals(id)){
                z.setPocetPoistencov(z.getPocetPoistencov()+1);
                z.getPoistenci().add(osoba);
                return z.getPoistenci().size() + "";
            }
        }
        return null;
    }
    

    @GET
    @Path("/{id}/{no}")
    @Produces(MediaType.APPLICATION_XML)
    public Osoba getOsobu(@PathParam("id") String id, @PathParam("no") int poradoveCislo) {
     for (Poistenie z : poistenia){
            if (z.getIdZmluvy().equals(id)){
                if (z.getPoistenci().size() >= poradoveCislo){
                    return z.getPoistenci().get(poradoveCislo-1);
                }
            }
        }
        return null;
    }

    @DELETE
    @Path("/{id}/{no}")
    public void deletePerson(@PathParam("id") String id, @PathParam("no") int poradoveCislo) {
        for (Poistenie z : poistenia){
            if (z.getIdZmluvy().equals(id)){
                if (z.getPoistenci().size() >= poradoveCislo){
                    z.getPoistenci().remove(z.getPoistenci().get(poradoveCislo-1));
                    z.setPocetPoistencov(z.getPocetPoistencov()-1);
                }
            }
        }
    }
}
