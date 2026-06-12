package vsa;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("skuska")
public class SkuskaResource {
    private List<Skuska> skusky;
    
    public SkuskaResource() {
        Skuska skuska = new Skuska();
        skuska.setDen("utorok");
        skuska.setPredmet("VSA");
        skuska.setStudent(new ArrayList<>());
        
        skusky = new ArrayList<>();
        skusky.add(skuska);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String createSkuska(Skuska skuska) {
        if (skuska != null) {
            for (Skuska s : skusky) {
                if (s.getPredmet().equals(skuska.getPredmet())) {
                    return "duplicita";
                }
            }
            
            if (skuska.getStudent() == null) {
                skuska.setStudent(new ArrayList<>());
            }
            skusky.add(skuska);
            return skuska.getPredmet();
        }
        
        return "";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSkuska(@QueryParam("student") String meno) {
        if (meno != null && !meno.isEmpty()) {
            String idSkusok = "";
            for (Skuska s : skusky) {
                if (s.getStudent()!=null && s.getStudent().contains(meno)) {
                    idSkusok += s.getPredmet() + " ";
                }
            }
            
            if (idSkusok.isEmpty()) {
                return "ziadne predmety";
            }
            
            return idSkusok.trim();
        }
        
        String idSkusok = "";
        for (Skuska s : skusky) {
            idSkusok += s.getPredmet() + " ";
        }
        
        return idSkusok.trim();
    }
    
    
    
    @Path("{predmet}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCountPredmet(@PathParam("predmet") String predmet) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                return s.getStudent() == null ? "0" : String.valueOf(s.getStudent().size());
            }
        }
        
        return "0";
    }
    
    @Path("{predmet}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getPredmet(@PathParam("predmet") String predmet) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                return s;
            }
        }
        
        return null;
    }
    
    @Path("{predmet}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postStudent(@PathParam("predmet") String predmet, String meno) {
        if (meno.isEmpty()) {
            return "";
        }
        
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                if (s.getStudent() == null) {
                    s.setStudent(new ArrayList<>());
                }
                
                
                if (!s.getStudent().contains(meno)) {
                    s.getStudent().add(meno);
                    return s.getDen();
                }
                
                return s.getDen() + "duplicita";
            }
        }
        
        return "predmet neexistuje";
    }
}