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
        skusky = new ArrayList<>();

        Skuska skuska = new Skuska();
        skuska.setPredmet("VSA");
        skuska.setDen("utorok");
        skuska.setStudent(new ArrayList<String>());

        skusky.add(skuska);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String vytvorSkusku(Skuska skuska) {
        if (skuska == null || skuska.getPredmet() == null || skuska.getPredmet().isEmpty()) {
            return "";
        }

        for (Skuska s : skusky) {
            if (s.getPredmet().equals(skuska.getPredmet())) {
                return "duplicita";
            }
        }

        if (skuska.getStudent() == null) {
            skuska.setStudent(new ArrayList<String>());
        }

        skusky.add(skuska);
        return skuska.getPredmet();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String vratPredmety(@QueryParam("student") String student) {
        String vysledok = "";

        for (Skuska s : skusky) {
            if (student == null || student.isEmpty()) {
                vysledok += s.getPredmet() + " ";
            } else if (s.getStudent() != null && s.getStudent().contains(student)) {
                vysledok += s.getPredmet() + " ";
            }
        }

        vysledok = vysledok.trim();

        if (student != null && !student.isEmpty() && vysledok.isEmpty()) {
            return "ziadne predmety";
        }

        return vysledok;
    }

    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pocetStudentov(@PathParam("predmet") String predmet) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                if (s.getStudent() == null) {
                    return "0";
                }
                return String.valueOf(s.getStudent().size());
            }
        }

        return "0";
    }

    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska vratSkusku(@PathParam("predmet") String predmet) {
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
    public String prihlasStudentaNaSkusku(@PathParam("predmet") String predmet, String student) {
        for (Skuska s : skusky) {
            if (s.getPredmet().equals(predmet)) {
                if (s.getStudent() == null) {
                    s.setStudent(new ArrayList<String>());
                }

                if (s.getStudent().contains(student)) {
                    return s.getDen() + " duplicita";
                }

                s.getStudent().add(student);
                return s.getDen();
            }
        }

        return "predmet neexistuje";
    }
}