package com.mycompany.langaggagaga;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("slovnik")
@Singleton
public class SampleResource {

    private Slovnik preklady = new Slovnik();

    /*
	@Inject
	@ConfigProperty(name = "message")
	private String message;

	@GET
	public Response message() {
		return Response.ok(message).build();
	}*/
    private void SetJazyk(String jazyk, HashMap<String, String> preklady) {

        this.preklady.setPreklady(jazyk, preklady);
    }

    private void SetPreklady(String jazyk, HashMap<String, String> preklady) {
        for (Map.Entry<String, String> entry : preklady.entrySet()) {
            this.preklady.getPreklady().get(jazyk).put(entry.getKey(), entry.getValue());
        }
    }

    @PostConstruct
    private void init() {
        //  Slovnik slovnik = new Slovnik();
        HashMap<String, String> skTranslations = new HashMap<>();
        skTranslations.put("ahoj", "hi");
        this.SetJazyk("sk", skTranslations);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        if (this.preklady.getPreklady().isEmpty() || this.preklady == null) {
            return "Prazdny slovnik";
        } else {
            String msg = "";
            for (String jazyky : preklady.getPreklady().keySet()) {
                msg = msg + jazyky + " ";
            }

            return msg;
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{lang}/{word}")
    public String getMessage2(@PathParam("lang") String lang, @PathParam("word") String word) {
        if (!this.preklady.getPreklady().containsKey(lang)) {
            return "Neznamy jazyk";
        } else if (!this.preklady.getPreklady().get(lang).containsKey(word)) {
            return "Nezname slovo";
        } else {
            for (String jazyky : preklady.getPreklady().keySet()) {
                if (jazyky.equals(lang)) {
                    return preklady.getPreklady().get(lang).get(word);
                }
            }

        }
        return null;
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{lang}/{word}")
    public void PUTMessage(@PathParam("lang") String lang, @PathParam("word") String word, String preklad) {
        HashMap<String, String> trans = new HashMap<>();
        trans.put(word, preklad);
        if (!this.preklady.getPreklady().containsKey(lang)) {
            this.preklady.setPreklady(lang, trans);
        } else if (!this.preklady.getPreklady().get(lang).containsKey(word)) {
            this.preklady.getPreklady().get(lang).put(word, preklad);
        } else {
            this.SetPreklady(lang, trans);
        }

    }

    @DELETE
    @Path("/{lang}")
    public void DeleteMessage(@PathParam("lang") String lang) {
        if (!this.preklady.getPreklady().containsKey(lang)) {
            this.preklady.getPreklady().remove(lang);
        }

    }

    @DELETE
    @Path("/{lang}/{word}")
    public void DeleteMessage2(@PathParam("lang") String lang, @PathParam("word") String word) {
        HashMap<String, String> trans = new HashMap<>();
        if (this.preklady.getPreklady().containsKey(lang)) {
            if (this.preklady.getPreklady().get(lang).containsKey(word)) {
                this.preklady.getPreklady().get(lang).remove(word);
            }
        }

    }

}
