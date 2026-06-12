package rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Gmod4
 */

@javax.inject.Singleton
@Path("skuska")
public class MyResources {

    List<Skuska> skusky = new ArrayList<>();

    /**
     * Creates a new instance of SkuskaResource
     */
    public MyResources() {
        Skuska s = new Skuska();
        s.setPredmet("VSA");
        s.setDen("utorok");
        this.skusky.add(s);
    }


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String post1(Skuska xml) {

        if (skusky.stream().anyMatch(zmluva1 -> zmluva1.getPredmet().equals(xml.getPredmet())))
            return "duplicita";
        this.skusky.add(xml);
        return xml.getPredmet();
    } // done

    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPredmet(@PathParam("predmet") String predmet) {

        Skuska skuska = this.skusky.stream().filter(zmluvaa -> zmluvaa.getPredmet().equals(predmet)).findFirst().orElse(null);

        if (skuska == null)
            return "0";
        return "" + skuska.getStudent().size();

    }

    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getSkuskaPredmetXML(@PathParam("predmet") String predmet) {

        Skuska skuska = this.skusky.stream().filter(zmluvaa -> zmluvaa.getPredmet().equals(predmet)).findFirst().orElse(null);
        if (skuska == null){
            return null;
        }
        return skuska;
    } // done

    @POST
    @Path("{predmet}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String prihlasStudenta(@PathParam("predmet") String predmet, String meno) {
        Skuska skuska = this.skusky.stream().filter(zmluvaa -> zmluvaa.getPredmet().equals(predmet)).findFirst().orElse(null);
        if (skuska == null)
            return "predmet neexistuje";
        if (!skuska.getStudent().contains(meno)) {
            skuska.getStudent().add(meno);
            return skuska.getPredmet();
        }
        else {
            return skuska.getPredmet() + " duplicita"; 
        }
        
        
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@QueryParam("student") String student) {

        if (student == null) {
            return "";
        }
//        if (student == ""){
//            String st = "";
//            for (Skuska s : this.skusky) {
//                st += s.getPredmet();
//            }
//            return st;
//        }
        List<String> predmety = new ArrayList<>();

        for (Skuska s : this.skusky) {
            if (s.getStudent().contains(student)) {
                predmety.add(s.getPredmet());
            }
        }

        String slovo = "";
        for (String p : predmety) {
            slovo = slovo + " " + p;
        }
        if (slovo == "") {
            return "ziadne predmety";
        }

        return slovo;
    } // done?

}
