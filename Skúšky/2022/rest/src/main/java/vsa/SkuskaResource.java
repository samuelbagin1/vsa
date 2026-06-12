/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("skuska")
@Singleton
public class SkuskaResource {
    
    private List<Skuska> skusky;
    
    public SkuskaResource() {
        this.skusky = new ArrayList();
        
        Skuska s = new Skuska();
        s.setDen("utorok");
        s.setPredmet("VSA");
        
        skusky.add(s);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postSkuska(Skuska content) {
       for (Skuska s : skusky) {
           if (s.getPredmet().equals(content.getPredmet())) {
               return "duplicita";
           }
       }
       skusky.add(content);
       return content.getPredmet();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSkusky(@QueryParam("student") @DefaultValue("") String student) {
        String ids = "";
        if (student.equals("")) {
            for (Skuska s : skusky) {
                ids = ids + s.getPredmet() + " ";
            }
        }
        else {
            for (Skuska s : skusky) {
                if (s.getStudenti() == null) {
                    continue;
                }
                for (String st : s.getStudenti()) {
                    if (st.equals(student)) {
                        ids = ids + s.getPredmet() + " ";
                        break;
                    }
                }
            }
        }
        if (ids.equals("")) {
            return "ziadne predmety";
        }
        return ids;
    }
    
    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocetStudentov(@PathParam("predmet") String predmet) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                if (s.getStudenti() != null) {
                    return Integer.toString(s.getStudenti().size());
                }
                else {
                    return "0";
                }
            }
        }
        return null;
    }
    
    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getSkuska(@PathParam("predmet") String predmet) {
        for (Skuska s : skusky) {
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
    public String postStudent(@PathParam("predmet") String predmet, String content) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                if (s.getStudenti() == null) {
                    List<String> l = new ArrayList();
                    l.add(content);
                    s.setStudenti(l);
                    return s.getDen();
                }
                for (String st : s.getStudenti()) {
                    if (st.equals(content)) {
                        return s.getDen() + " duplicita";
                    }
                }
                s.getStudenti().add(content);
                return s.getDen();
            }
        }
        return "predmet neexistuje";
    }
}