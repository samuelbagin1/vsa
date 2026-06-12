/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author edu
 */
public class DbApp {

    public static void main(String[] args) {
    }
    
    public static Predmet novyPredmet(EntityManager em, String nazov, String meno) throws Exception {
        if (nazov == null || nazov.isBlank()) {
            throw new Exception("chyba");
        }
        
        Predmet predmet = em.find(Predmet.class, nazov);
        if (predmet != null) {
            throw new Exception("duplicita");
        }
        
        em.getTransaction().begin();
        
        predmet = new Predmet();
        predmet.setNazov(nazov);
        predmet.setUcitelia(new ArrayList<>());
        
        if (meno != null && !meno.isBlank()) {
            Docent docent = new Docent();
            docent.setMeno(meno);
            docent.setPredmety(new ArrayList<>());
            
            predmet.setPrednasajuci_id(docent);
            predmet.getUcitelia().add(docent);
            docent.getPredmety().add(predmet);
            
            em.persist(docent);
        }
        
        em.persist(predmet);
        em.getTransaction().commit();
        
        return predmet;
        
    }
}
