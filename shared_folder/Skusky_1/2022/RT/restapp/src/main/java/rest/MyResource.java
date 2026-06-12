package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@javax.inject.Singleton
@Path("poistenie")
public class MyResource {

    private List<Zmluva> zmluvy = new ArrayList<>();

    public MyResource() {
        Zmluva zmluva = new Zmluva();
        zmluva.setId("Z123");
        zmluva.setMajitel("Mrkvicka");
        this.zmluvy.add(zmluva);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void createPoistenie(Zmluva zmluva){
        if (zmluva.getId() == null)
            return;
        if (zmluvy.stream().anyMatch(zmluva1 -> zmluva1.getId().equals(zmluva.getId())))
            return;
        this.zmluvy.add(zmluva);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Zmluva getZmluva(@PathParam("id") String id){
        //return this.zmluvy.stream().filter(zmluva -> zmluva.getId().equals(id)).findFirst().orElse(null);
        for (Zmluva zmluva: this.zmluvy) {
            if (zmluva.getId().equals(id))
                return zmluva;
        }
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocet(@PathParam("id") String id) {
        Zmluva zmluva = this.zmluvy.stream().filter(zmluvaa -> zmluvaa.getId().equals(id)).findFirst().orElse(null);
        if (zmluva == null)
            return "neplatna zmluva";
        return "" + zmluva.getPoistenec().size();
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addOsoba(@PathParam("id") String id,String meno) {
        Zmluva zmluva = this.zmluvy.stream().filter(zmluvaa -> zmluvaa.getId().equals(id)).findFirst().orElse(null);
        if (zmluva == null)
            return "neplatna zmluva";
        if (!zmluva.getPoistenec().contains(meno))
            zmluva.getPoistenec().add(meno);
        int i = 0;
        for (String menicko: zmluva.getPoistenec()) {
            if (menicko.equals(meno))
                return ""+i;
            i++;
        }
        return "blabla";
    }

    @GET
    @Path("{id}/{no}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getnieco(@PathParam("id") String id,@PathParam("no") Long poradoveCislo){
        if (id == null || poradoveCislo == null)
            return null;
        Zmluva zmluva = this.zmluvy.stream().filter(zmluvaa -> zmluvaa.getId().equals(id)).findFirst().orElse(null);
        if (zmluva == null)
            return "neplatna zmluva";
        if (poradoveCislo > zmluva.getPoistenec().size() - 1 || poradoveCislo < 0)
            return "neplatna osoba";
        return zmluva.getPoistenec().get(poradoveCislo.intValue());
    }

    @DELETE
    @Path("{id}/{no}")
    public void deletePerson(@PathParam("id") String id,@PathParam("no") Long poradoveCislo){
        if (id == null || poradoveCislo == null)
            return;
        Zmluva zmluva = this.zmluvy.stream().filter(zmluvaa -> zmluvaa.getId().equals(id)).findFirst().orElse(null);
        if (zmluva == null)
            return;
        if (poradoveCislo > zmluva.getPoistenec().size() - 1)
            return;
        zmluva.getPoistenec().remove(poradoveCislo.intValue());
    }

}
