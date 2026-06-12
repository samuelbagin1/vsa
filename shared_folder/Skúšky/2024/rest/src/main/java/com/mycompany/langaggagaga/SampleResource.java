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

@Singleton
@Path("slovnik")
public class SampleResource {
    private Map<String, Map<String, String>> slovnik; // lang - word - preklad
    
    public SampleResource() {
        slovnik = new HashMap<>();
    }
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSlovnik() {
        if (slovnik == null || slovnik.isEmpty()) {
            return "Prazdny slovnik";
        }
        
        String ret = "";
        for (String lang : slovnik.keySet()) {
            if (!slovnik.get(lang).isEmpty()) {
                ret += lang + " ";
            }
        }
        
        return ret.isEmpty() ? "Prazdny slovnik" : ret.trim();
    }
    
    @GET
    @Path("{lang}/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPreklad(@PathParam("lang") String lang, @PathParam("word") String word) {
        if (lang == null || lang.isEmpty()) {
            return "Neznamy jazyk";
        }
        
        Map<String, String> jazyk = slovnik.get(lang);
        if (jazyk == null) {
            return "Neznamy jazyk";
        }
        
        if (word == null || word.isEmpty()) {
            return "Nezname slovo";
        }
        
        String preklad = jazyk.get(word);
        if (preklad == null) {
            return "Nezname slovo";
        }
        
        return preklad;
    }
    
    
    
    @PUT
    @Path("{lang}/{word}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putPreklad(@PathParam("lang") String lang, @PathParam("word") String word, String preklad) {
        if (lang == null || lang.isEmpty()) {
            return;
        }
        
        if (slovnik.get(lang) == null) {
            slovnik.put(lang, new HashMap<>());
        }
        
        if (word == null || word.isEmpty()) {
            return;
        }
        
        if (slovnik.get(lang).get(word) == null) {
            slovnik.get(lang).put(word, preklad);
            return;
        }
        
        slovnik.get(lang).replace(word, preklad);
    }
    
    @DELETE
    @Path("{lang}")
    public void deleteLang(@PathParam("lang") String lang) {
        if (lang == null || lang.isEmpty()) {
            return;
        }
        
        slovnik.remove(lang);
    }
    
    @DELETE
    @Path("{lang}/{word}")
    public void deleteWord(@PathParam("lang") String lang, @PathParam("word") String word) {
        if (lang == null || lang.isEmpty() || word == null || word.isEmpty()) {
            return;
        }
        
        if (slovnik.get(lang)!=null) {
            slovnik.get(lang).remove(word);
        }
    }
}
