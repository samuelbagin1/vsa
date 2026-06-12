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
import java.util.List;

/**
 *
 * @author edu
 */
public class DbApp {

    public static void main(String[] args) {
    }
    
    public static Predmet novyPredmet(EntityManager em, String nazov, String meno) throws Exception {
        if (nazov == null || nazov.isEmpty()) {
            throw new Exception("chyba");
        }
        
        Predmet predmet = em.find(Predmet.class, nazov);
        if (predmet != null) {
            throw new Exception("duplicita");
        }
        
        em.getTransaction().begin();
        Predmet p = new Predmet();
        p.setNazov(nazov);
        p.setCviciaci(new ArrayList<>());
        
        if (meno != null && !meno.isEmpty()) {
            Docent doc = new Docent();
            doc.setMeno(meno);
            doc.setCvicenie(new ArrayList<>());
            doc.setPrednaska(new ArrayList<>());
            
            em.persist(doc);
            em.flush();
            
            p.setPrednasajuci(doc);
            p.getCviciaci().add(doc);
            doc.getCvicenie().add(p);
            doc.getPrednaska().add(p);
           
        }
        
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }
}
