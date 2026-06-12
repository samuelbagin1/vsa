/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    private static int BODY;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em= emf.createEntityManager();
        
        Osoba osoba = new Osoba();
        osoba.setId(1L);
        osoba.setMeno("Jano");
        osoba.setUcty(new ArrayList<>());
        Ucet ucet = new Ucet();
        ucet.setId(1L);
        ucet.setStav(500.0);
        ucet.setMajitel(osoba);
        
        Osoba osoba2 = new Osoba();
        osoba2.setId(2L);
        osoba2.setMeno("Mato");
        osoba2.setUcty(new ArrayList<>());
        Ucet ucet2 = new Ucet();
        ucet2.setId(2L);
        ucet2.setStav(500.0);
        ucet2.setMajitel(osoba2);
        
        
        
      
        em.getTransaction().begin();
        em.persist(osoba);
        em.persist(osoba2);
        em.persist(ucet);
        em.persist(ucet2);
        em.getTransaction().commit();
        
        
        
       try { Banka.vytvorPrevod(em,500L,1L,2L);} catch (Exception e){};
       try { Banka.realizujPrevody(em);} catch (Exception e){};
    }
    
}
